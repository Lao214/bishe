package com.example.mymusic.service.impl;



import com.example.mymusic.dao.AdminDao;
import com.example.mymusic.entity.Admin;
import com.example.mymusic.service.AdminService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 管理员(Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-01-07 09:52:20
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param adminNo 主键
     * @return 实例对象
     */
    @Override
    public Admin queryAdminByAdminNo(String adminNo) {
        return this.adminDao.queryAdminByAdminNo (adminNo);
    }

    @Override
    public   Admin queryAdminById(Long id) {
        return this.adminDao.queryAdminById (id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAllByLimit(int offset, int limit) {
        return this.adminDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Long queryAllCount() {
        return this.adminDao.queryAllCount();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Admin> queryAll(int offset, int limit,Admin admin) {
        return this.adminDao.queryAll(offset, limit,admin);
    }

    @Override
    public List<Admin> queryAdminByCondition(Admin admin) {
        return this.adminDao.queryAdminByCondition(admin);
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {

        this.adminDao.addAdmin(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        this.adminDao.updateAdmin(admin);
        return this.adminDao.queryAdminByAdminNo(admin.getAdminNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param adminNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByAdminNo(String adminNo) {
        return this.adminDao.deleteByAdminNo(adminNo) > 0;
    }


    /**
     * 假删除
     *
     * @param adminNo 主键
     * @return 是否成功
     */
    @Override
    public int deleteAdminByAdminNo(String adminNo) {
        return this.adminDao.deleteAdminByAdminNo(adminNo);
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 登录
     * @Date 14:17 2022/1/19
     * @Param 
     * @return 
     **/
    @Override
        public Admin login(Admin admin) {
        return this.adminDao.login (admin);
    }
}
