package tbgt.persistence;

import tbgt.domain.TopSessionKey;

public interface TopSessionKeyMapper {

    public TopSessionKey getTopSessionKeyId(int id);

    public TopSessionKey getLastTopSessionKey();

    public void insertTopSessionKey(TopSessionKey topSessionKey);

    public void updateTopSessionKey(TopSessionKey topSessionKey);

    public void deleteTopSessionKey(int id);
}
