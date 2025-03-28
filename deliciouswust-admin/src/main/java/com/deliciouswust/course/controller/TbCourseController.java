package com.deliciouswust.course.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.deliciouswust.common.annotation.Log;
import com.deliciouswust.common.core.controller.BaseController;
import com.deliciouswust.common.core.domain.AjaxResult;
import com.deliciouswust.common.enums.BusinessType;
import com.deliciouswust.course.domain.TbCourse;
import com.deliciouswust.course.service.ITbCourseService;
import com.deliciouswust.common.utils.poi.ExcelUtil;
import com.deliciouswust.common.core.page.TableDataInfo;

/**
 * 课程管理Controller
 * 
 * @author xiezujia
 * @date 2024-09-07
 */
@RestController
@RequestMapping("/course/course")
public class TbCourseController extends BaseController
{
    @Autowired
    private ITbCourseService tbCourseService;

    /**
     * 查询课程管理列表
     */
    @PreAuthorize("@ss.hasPermi('course:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(TbCourse tbCourse)
    {
        startPage();
        List<TbCourse> list = tbCourseService.selectTbCourseList(tbCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
    @PreAuthorize("@ss.hasPermi('course:course:export')")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbCourse tbCourse)
    {
        List<TbCourse> list = tbCourseService.selectTbCourseList(tbCourse);
        ExcelUtil<TbCourse> util = new ExcelUtil<TbCourse>(TbCourse.class);
        util.exportExcel(response, list, "课程管理数据");
    }

    /**
     * 获取课程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbCourseService.selectTbCourseById(id));
    }

    /**
     * 新增课程管理
     */
    @PreAuthorize("@ss.hasPermi('course:course:add')")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbCourse tbCourse)
    {
        return toAjax(tbCourseService.insertTbCourse(tbCourse));
    }

    /**
     * 修改课程管理
     */
    @PreAuthorize("@ss.hasPermi('course:course:edit')")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbCourse tbCourse)
    {
        return toAjax(tbCourseService.updateTbCourse(tbCourse));
    }

    /**
     * 删除课程管理
     */
    @PreAuthorize("@ss.hasPermi('course:course:remove')")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbCourseService.deleteTbCourseByIds(ids));
    }
}
