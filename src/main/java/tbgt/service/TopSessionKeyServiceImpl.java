package tbgt.service;

import tbgt.domain.TopSessionKey;
import tbgt.persistence.TopSessionKeyMapper;

public class TopSessionKeyServiceImpl implements TopSessionKeyService {

    private TopSessionKeyMapper topSessionKeyMapper;

    public void setTopSessionKeyMapper(TopSessionKeyMapper topSessionKeyMapper) {
        this.topSessionKeyMapper = topSessionKeyMapper;
    }

    @Override
    public TopSessionKey getTopSessionKeyId(int id) {
        return topSessionKeyMapper.getTopSessionKeyId(id);
    }

    @Override
    public TopSessionKey getLastTopSessionKey() {
        return topSessionKeyMapper.getLastTopSessionKey();
    }

    @Override
    public void insertTopSessionKey(TopSessionKey topSessionKey) {
        topSessionKeyMapper.insertTopSessionKey(topSessionKey);
    }

    @Override
    public void updateTopSessionKey(TopSessionKey topSessionKey) {
        topSessionKeyMapper.updateTopSessionKey(topSessionKey);
    }

    @Override
    public void deleteTopSessionKey(int id) {
        topSessionKeyMapper.deleteTopSessionKey(id);
    }
}
