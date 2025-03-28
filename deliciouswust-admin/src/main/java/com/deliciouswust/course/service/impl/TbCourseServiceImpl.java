package com.deliciouswust.course.service.impl;

import java.util.List;
import com.deliciouswust.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliciouswust.course.mapper.TbCourseMapper;
import com.deliciouswust.course.domain.TbCourse;
import com.deliciouswust.course.service.ITbCourseService;

/**
 * 课程管理Service业务层处理
 * 
 * @author xiezujia
 * @date 2024-09-07
 */
@Service
public class TbCourseServiceImpl implements ITbCourseService 
{
    @Autowired
    private TbCourseMapper tbCourseMapper;

    /**
     * 查询课程管理
     * 
     * @param id 课程管理主键
     * @return 课程管理
     */
    @Override
    public TbCourse selectTbCourseById(Long id)
    {
        return tbCourseMapper.selectTbCourseById(id);
    }

    /**
     * 查询课程管理列表
     * 
     * @param tbCourse 课程管理
     * @return 课程管理
     */
    @Override
    public List<TbCourse> selectTbCourseList(TbCourse tbCourse)
    {
        return tbCourseMapper.selectTbCourseList(tbCourse);
    }

    /**
     * 新增课程管理
     * 
     * @param tbCourse 课程管理
     * @return 结果
     */
    @Override
    public int insertTbCourse(TbCourse tbCourse)
    {
        tbCourse.setCreateTime(DateUtils.getNowDate());
        return tbCourseMapper.insertTbCourse(tbCourse);
    }

    /**
     * 修改课程管理
     * 
     * @param tbCourse 课程管理
     * @return 结果
     */
    @Override
    public int updateTbCourse(TbCourse tbCourse)
    {
        tbCourse.setUpdateTime(DateUtils.getNowDate());
        return tbCourseMapper.updateTbCourse(tbCourse);
    }

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteTbCourseByIds(Long[] ids)
    {
        return tbCourseMapper.deleteTbCourseByIds(ids);
    }

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteTbCourseById(Long id)
    {
        return tbCourseMapper.deleteTbCourseById(id);
    }
}
