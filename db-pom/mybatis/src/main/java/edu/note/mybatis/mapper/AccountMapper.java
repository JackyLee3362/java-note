package edu.note.mybatis.mapper;

import edu.note.mybatis.model.Account;

/**
 * @author jackylee
 * @date 2025/9/25 13:11
 */
public interface AccountMapper {

    Account selectById(int id);

    void insertAccount(Account account);

}
