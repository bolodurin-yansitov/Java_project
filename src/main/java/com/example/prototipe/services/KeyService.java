package com.example.prototipe.services;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.daos.KeyDao;
import com.example.prototipe.dtos.KeyDto;
import com.example.prototipe.entities.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.prototipe.common.utils.ValidationUtils.*;

@Service
@RequiredArgsConstructor
public class KeyService {
    private final KeyDao keyDao;

    public KeyDto getKeyByOpenKey(Long openKey) throws ValidationException {
        validateIsNull(openKey, "Open key is not provide");

        Key key = keyDao.findByOpenKey(openKey);
        validateIsNull(key, "No key with such open key " + openKey);

        return buildKeyDtoFromKey(key);
    }

    private KeyDto buildKeyDtoFromKey(Key key){
        KeyDto keyDto = new KeyDto(
                key.getOpenKey(), key.getUserId(),
                key.getCreatingTime(), key.getDeletingTime());

        return keyDto;
    }

    public KeyDto getKeyByUserId(Long userId) throws ValidationException {
        validateIsNull(userId, "Open key is not provide");

        Key key = keyDao.findByUserId(userId);
        validateIsNull(key, "No key with such open key " + userId);

        return buildKeyDtoFromKey(key);
    }
}
