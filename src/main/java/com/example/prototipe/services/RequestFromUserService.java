package com.example.prototipe.services;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.daos.KeyDao;
import com.example.prototipe.daos.RequestFromUserDao;
import com.example.prototipe.dtos.KeyDto;
import com.example.prototipe.dtos.RequestFromUserDto;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.Key;
import com.example.prototipe.entities.RequestFromUser;
import com.example.prototipe.entities.Users;
import com.example.prototipe.entities.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.*;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.prototipe.common.utils.ValidationUtils.*;
import static com.example.prototipe.entities.enums.StatusOfRequest.SENT;

@Service
@RequiredArgsConstructor
public class RequestFromUserService {
    private final RequestFromUserDao requestFromUserDao;
    private final KeyDao keyDao;

    public RequestFromUserDto getRequestFromUser(Long id)
            throws ValidationException {
        validateIsNull(id, "Request id is not provide");

        RequestFromUser requestFromUser = requestFromUserDao.findOne(id);
        validateIsNull(requestFromUser, "No request from user with such id " +
                id);

        return buildRequestFromUserDtoFromRequestFromUser(requestFromUser);
    }

    private RequestFromUserDto buildRequestFromUserDtoFromRequestFromUser(
            RequestFromUser requestFromUser) {
        return new RequestFromUserDto(
                requestFromUser.getRequestId(),
                requestFromUser.getTimeOfRequesting(),
                requestFromUser.getStatusOfRequest(),
                requestFromUser.getReasonOfRequesting(),
                buildKeyDtoFromKey(requestFromUser.getKey()),
                buildUserDtoFromUser(requestFromUser.getUser()));
    }

    private UsersDto buildUserDtoFromUser(Users user) {
        return new UsersDto(
                user.getId(), user.getRole(), user.getFname(),
                user.getSname(), user.getFaculNum(), user.getCourseNum(),
                user.getEmail());
    }

    private KeyDto buildKeyDtoFromKey(Key key) {
        return new KeyDto(
                key.getOpenKey(), key.getUserId(),
                key.getCreatingTime(), key.getDeletingTime());
    }

    public RequestFromUserDto getRequestFromUserByUserId(Long userId)
            throws ValidationException {
        validateIsNull(userId, "User id is not provide");

        Users user = requestFromUserDao.findUserByUsersId(userId);
        validateIsNull(user, "No user with such user id " + userId);

        List<RequestFromUser> requestsFromUser = user.getRequestsFromUser();

        RequestFromUser lastRequestFromUser = new RequestFromUser();
        int count = 0;

        for(RequestFromUser j : requestsFromUser){
            if(j.getStatusOfRequest() == SENT){
                lastRequestFromUser = j;
                count++;
            }
        }

        if(count > 1){
            validateIsNull(null, "Quantity of sending requests from " +
                    "user is bigger one " + userId);
        }
        else if(count == 0){
            validateIsNull(null, "No sending requests from such user " +
                    userId);
        }

        return buildRequestFromUserDtoFromRequestFromUser(lastRequestFromUser);
    }

    public List<RequestFromUserDto> getRequestsFromUsersForHeadmanByHeadmanId(
            Long headmanId) throws ValidationException{
        validateIsNull(headmanId, "Headman id is not provide");

        Users headman = this.requestFromUserDao.findUserByUsersId(headmanId);
        validateIsNull(headman, "No headman with such id");
        if(headman.getRole() == Role.STUDENT)
            validateIsNull(null, "It is not headman");

        return headman.getHeadmanRequest().stream()
                .map(this::buildRequestFromUserDtoFromRequestFromUser)
                .collect(Collectors.toList());
    }

    public KeyDto createKeyByRequest(Long userId) throws Exception{
        validateIsNull(userId, "User id is not provide");

        Users user = requestFromUserDao.findUserByUsersId(userId);
        validateIsNull(user, "No user with such user id " + userId);

        Users headman = requestFromUserDao.findHeadmanByUserId(userId);
        validateIsNull(headman, "ERROR No headman for this user " +
                "with id " + userId);

        if(headman.getTimeOfRequesting().getStartOfSubmission()
                .isAfter(LocalDateTime.now()) ||
           headman.getTimeOfRequesting().getEndOfSubmission().
                   isBefore(LocalDateTime.now()))
            validateIsNull(null, "ERROR You can not create " +
                    "request now because your headman appointed another time");

        for(Key key : requestFromUserDao.findKeysByUserId(userId))
        {
            if(key.getDeletingTime().isBefore(LocalDateTime.now()))
                keyDao.delete(key);
            else validateIsNull(null, "Repeated creating of key and " +
                    "creating request by this user with id " + userId);
        }

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PNG", "SUN");
        keyGen.initialize(1024, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        //todo Upgrade system of deleting time for end of submission
        Key key = new Key(privateKey.getEncoded(), publicKey.getEncoded(),
                userId, LocalDateTime.now(), LocalDateTime.now().plusDays(5));

        keyDao.save(key);

        return buildKeyDtoFromKey(key);
    }

    public void createRequestFromUser(Long userId,
                                      RequestFromUserDto requestFromUserDto)
            throws ValidationException, NoSuchAlgorithmException,
            NoSuchProviderException, InvalidKeySpecException,
            InvalidKeyException, IOException,
            SignatureException{

        validateIsNull(userId, "User id is not provide");
        validateIsNull(requestFromUserDto, "Request from user DTO " +
                "is not provide");
        validateIsNull(requestFromUserDto.getUser(), "User in request DTO " +
                "is not provide");
        validateIsNull(requestFromUserDto.getKey(), "Key DTO is not provide");
        validateIsNotNull(requestFromUserDto.getRequestId(), "Can not create " +
                "request with existing id");
        //todo Add all checks

        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");

        dsa.initSign(keyFactory.generatePrivate(new PKCS8EncodedKeySpec(
                requestFromUserDao.findKeysByUserId(userId).get(0).
                        getCloseKey())));

        checkFileByPrivateKey(requestFromUserDto, dsa);

        RequestFromUser newRequest = new RequestFromUser(null,
                requestFromUserDto.getFile(), requestFromUserDto.getProof(),
                (short) 1, requestFromUserDto.getReasonOfRequesting().getValue(),
                LocalDateTime.now());

        requestFromUserDao.save(newRequest);
    }

    private void checkFileByPrivateKey(RequestFromUserDto requestFromUserDto,
                                       Signature dsa)
            throws IOException, ValidationException, SignatureException{

        File file = new File("");
        OutputStream os = new FileOutputStream(file);
        os.write(requestFromUserDto.getSignFile());
        os.close();

        FileInputStream fileIS = new FileInputStream(file);
        BufferedInputStream bufIS = new BufferedInputStream(fileIS);

        byte[] buffer = new byte[32768];
        int len;

        while ((len = bufIS.read(buffer)) >= 0)
            dsa.update(buffer, 0, len);

        bufIS.close();

        if(dsa.sign() != requestFromUserDto.getFile())
            validateIsNull(null, "GLOBAL ERROR with file is not sign file");
    }
}
