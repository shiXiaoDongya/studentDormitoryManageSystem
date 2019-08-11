package team.sdm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.sdm.mapper.TeacherMapper;
import team.sdm.po.Teacher;
import team.sdm.service.ITeacherService;
@Service
public class TeacherService implements ITeacherService {
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public List<Teacher> getTeacherList() {
		// TODO Auto-generated method stub
		return teacherMapper.getTeacherList();
	}

	@Override
	public List<Long> getTeacherByBuidingId(int id) {
		// TODO Auto-generated method stub
		return teacherMapper.getTeacherByBuidingId(id);
	}

	@Override
	public Teacher login(String name, String password) {
		// TODO Auto-generated method stub
		return teacherMapper.login(name,password);
	}
	
	@Override
	public List<Teacher> getTeacherList1(String teacher_name,int start,int rows) {
		// TODO Auto-generated method stub
		return teacherMapper.getTeacherList1(teacher_name,start,rows);
	}



	@Override
	public int teacherCount(String teacher_name) {
		// TODO 自动生成的方法存根
		return teacherMapper.getTeacherCount(teacher_name);
	}

	@Override
	public int addteacher(Teacher teacher) {
		// TODO 自动生成的方法存根
		return teacherMapper.addTeacher(teacher);
	}

	@Override
	public int editTeacher(Teacher teacher) {
		// TODO 自动生成的方法存根
		return teacherMapper.editTeacher(teacher);
	}

	@Override
	public boolean deleteTeacher(long deleteId) {
		// TODO 自动生成的方法存根
		return teacherMapper.deleteTeacher(deleteId);
	}

	@Override
	public List<Teacher> teacherList(Teacher teacher, int page, int row) {
		// TODO 自动生成的方法存根
		return teacherMapper.exportTeacher(teacher,page,row);
	}
}
