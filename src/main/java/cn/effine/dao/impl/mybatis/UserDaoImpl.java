/**
 * @author effine
 * @Date 2015年10月13日  下午2:01:31
 * @email iballader#gmail.com
 * @site http://www.effine.cn
 */

package cn.effine.dao.impl.mybatis;

import cn.effine.dao.UserDao;
import cn.effine.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends DaoSupport implements UserDao {

    @Override
    public boolean signup(User user) {
        SqlSession session = DaoSupport.getSqlSession();
        int uid = session.insert("cn.effine.IUserOperation.add", user);
        session.commit();
        session.close();
        return uid != 0;
    }

    @Override
    public boolean signin(String username, String passwd) {
        SqlSession session = DaoSupport.getSqlSession();
        int uid = session.insert("cn.effine.IUserOperation.signin");
        session.commit();
        session.close();
        String name = "effine";
        User user = new User();
        if (name.equals(username) && BCrypt.checkpw(passwd, user.getPasswd())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean signout(int uid) {
        return false;
    }

    @Override
    public boolean killAccount() {

        return false;
    }

}
