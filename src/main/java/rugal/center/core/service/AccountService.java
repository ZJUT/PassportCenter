/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rugal.center.core.service;

import ml.rugal.sshcommon.page.Pagination;
import rugal.center.core.entity.Account;
import rugal.center.core.entity.Passport;

/**
 *
 * @author rugal
 */
public interface AccountService
{

    Integer countAccount(Passport bean);

    Account deleteByName(String name);

    Account findByName(String name);

    Pagination getPage(int pageNo, int pageSize);

    Account save(Account bean);

    Account updateAccount(Account bean);
}
