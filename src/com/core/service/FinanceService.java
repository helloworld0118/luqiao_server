package com.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.entity.BasePriceType;
import com.core.entity.BasePriceTypeExample;
import com.core.entity.BaseType;
import com.core.entity.DayBook;
import com.core.entity.DayBookExample;
import com.core.entity.Department;
import com.core.entity.Expense;
import com.core.entity.ExpenseExample;
import com.core.entity.LoanInfo;
import com.core.entity.LoanInfoExample;
import com.core.entity.LoanInfoExample.Criteria;
import com.core.entity.Project;
import com.core.entity.Staff;
import com.core.mapper.BasePriceTypeMapper;
import com.core.mapper.BaseTypeMapper;
import com.core.mapper.DayBookMapper;
import com.core.mapper.DepartmentMapper;
import com.core.mapper.ExpenseMapper;
import com.core.mapper.LoanInfoMapper;
import com.core.mapper.StaffMapper;
import com.core.util.PageBean;
import com.core.util.PageBeanModel;
import com.core.util.Utils;
import com.core.util.model.DayBookModel;
import com.core.util.model.ExpenseModel;
import com.core.util.model.LoanInfoModel;

@Service
public class FinanceService {

	@Autowired
	private LoanInfoMapper loanInfoMapper;

	@Autowired
	private DayBookMapper dayBookMapper;

	@Autowired
	private ExpenseMapper expenseMapper;

	@Autowired
	private BaseTypeMapper baseTypeMapper;
	
	@Autowired
	private BasePriceTypeMapper basePriceTypeMapper;

	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private StaffMapper staffMapper;

	
	public PageBean<LoanInfoModel> loanModelList(String database,int project) {
		Utils.setDatabase(database);
		List<LoanInfoModel> models =loanInfoMapper.selectModelsByExample(project);
		PageBean<LoanInfoModel> page = new PageBean<LoanInfoModel>(models);
		return page;
	}
	public PageBeanModel<LoanInfoModel> loanList(String database, int project,String startDate,String endDate,String person) {
		Utils.setDatabase(database);
		LoanInfoExample example = new LoanInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		if(null!=person&&!"".equals(person)) {
			criteria.andPersonLike("%"+person+"%");
		}
		
		List<LoanInfo> list = loanInfoMapper.selectByExample(example);
		PageBean<LoanInfo> temp = new PageBean<LoanInfo>(list);
		List<LoanInfoModel> models = new ArrayList<LoanInfoModel>();
		for (LoanInfo loanInfo : list) {
			Department dpt = departmentMapper.selectByPrimaryKey(database, loanInfo.getDepartment());
			String userName = "";
			try {
				int staffId = Integer.parseInt(loanInfo.getPresenter());
				userName = staffMapper.selectByPrimaryKey(database, staffId).getName();
			} catch (Exception e) {
				userName = loanInfo.getPresenter();
			}
			models.add(new LoanInfoModel(loanInfo.getPerson(), loanInfo.getPrice(), loanInfo.getDate(), dpt.getId(),
					dpt.getName(), userName, loanInfo.getReason()));
		}
		PageBeanModel<LoanInfoModel> page = new PageBeanModel<LoanInfoModel>(temp.getTotal(), models);
		return page;
	}
	
	
	public PageBeanModel<DayBookModel> dayBookList(String database, int project,String startDate,String endDate,String account) {
		Utils.setDatabase(database);
		DayBookExample example = new DayBookExample();
		DayBookExample.Criteria criteria= example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		if(null!=account&&!"".equals(account)) {
			criteria.andAccountLike("%"+account+"%");
		}
		List<DayBook> list = dayBookMapper.selectByExample(example);
		PageBean<DayBook> temp = new PageBean<DayBook>(list);
		List<DayBookModel> models = new ArrayList<DayBookModel>();
		for (DayBook dayBook : list) {
			Department dpt = departmentMapper.selectByPrimaryKey(database, dayBook.getDepartment());
			String userName = "";
			try {
				int staffId = Integer.parseInt(dayBook.getPresenter());
				userName = staffMapper.selectByPrimaryKey(database, staffId).getName();
			} catch (Exception e) {
				userName = dayBook.getPresenter();
			}
			BasePriceType bpt =basePriceTypeMapper.selectByPrimaryKey(database, dayBook.getBillType());
			BaseType baseType = baseTypeMapper.selectByPrimaryKey(database, dayBook.getIncomeType());
			models.add(new DayBookModel(dayBook.getVoucherno(), dayBook.getIncome(), dayBook.getIncomeType(), baseType.getName(), 
					dayBook.getPay(), dayBook.getPayFor(), dayBook.getDate(), userName, dayBook.getSummary(),dayBook.getAccount(),dpt.getId(),dpt.getName(),bpt.getName()));
		}
		PageBeanModel<DayBookModel> page = new PageBeanModel<DayBookModel>(temp.getTotal(), models);
		return page;
	}
	
	public List<ExpenseModel> expenseList(String database, int project,String date,int staff) {
		Utils.setDatabase(database);
		ExpenseExample example = new ExpenseExample();
		ExpenseExample.Criteria criteria = example.createCriteria();
		criteria.andProjectEqualTo(project);
		example.setOrderByClause("id DESC");
		if(null!=date&&!"".equals(date)) {
			criteria.andCreateDateLike(date+"%");
		}else {
			criteria.andCreateDateNotLike(Utils.getCurrentDate()+"%");
		}
		criteria.andPresenterEqualTo(staff);
		List<Expense> list = expenseMapper.selectByExample(example);
		List<ExpenseModel> models = new ArrayList<ExpenseModel>();
		for (Expense expense : list) {
			Department dpt = departmentMapper.selectByPrimaryKey(database, expense.getDepartment());
			String userName = expense.getPresenterName();
			BaseType baseType = baseTypeMapper.selectByPrimaryKey(database, expense.getBillType());
			BasePriceType priceType = basePriceTypeMapper.selectByPrimaryKey(database, expense.getPriceType());
			models.add(new ExpenseModel(expense.getPerson(), expense.getPrice(), expense.getDate(),expense.getCreateDate(), 
					priceType.getName(), baseType.getName(), dpt.getName(), userName,expense.getRemark()));
		}
		return models;
	}
	
	public PageBeanModel<ExpenseModel> expenseList(String database, int project,String startDate,String endDate,String person) {
		Utils.setDatabase(database);
		ExpenseExample example = new ExpenseExample();
		ExpenseExample.Criteria criteria = example.createCriteria();
		criteria.andProjectEqualTo(project);
		if (null!=startDate&&!"".equals(startDate)) {
			criteria.andDateBetween(startDate, endDate);
		}
		if(null!=person&&!"".equals(person)) {
			criteria.andPersonLike("%"+person+"%");
		}
		List<Expense> list = expenseMapper.selectByExample(example);
		PageBean<Expense> temp = new PageBean<Expense>(list);
		List<ExpenseModel> models = new ArrayList<ExpenseModel>();
		for (Expense expense : list) {
			Department dpt = departmentMapper.selectByPrimaryKey(database, expense.getDepartment());
			String userName = expense.getPresenterName();
			BaseType baseType = baseTypeMapper.selectByPrimaryKey(database, expense.getBillType());
			BasePriceType priceType = basePriceTypeMapper.selectByPrimaryKey(database, expense.getPriceType());
			models.add(new ExpenseModel(expense.getPerson(), expense.getPrice(), expense.getDate(), expense.getCreateDate(),
					priceType.getName(), baseType.getName(), dpt.getName(), userName,expense.getRemark()));
		}
		PageBeanModel<ExpenseModel> page = new PageBeanModel<ExpenseModel>(temp.getTotal(), models);
		return page;
	}
	
	public boolean addLoanInfo(String database, LoanInfo loanInfo) {
		Utils.setDatabase(database);
		int result = loanInfoMapper.insertSelective(loanInfo);
		return result == 1 ? true : false;
	}
	
	
	public boolean addDayBook(String database, DayBook dayBook) {
		Utils.setDatabase(database);
		int result = dayBookMapper.insertSelective(dayBook);
		return result == 1 ? true : false;
	}
	
	
	public boolean addExpense(String database, Expense expense) {
		Utils.setDatabase(database);
		int result = expenseMapper.insertSelective(expense);
		return result == 1 ? true : false;
	}
	
	public List<BasePriceType> getbasePriceTypeList(String database){
		Utils.setDatabase(database);
		BasePriceTypeExample bpTypeExample = new BasePriceTypeExample();
		bpTypeExample.createCriteria().andTypeEqualTo(4);
		return basePriceTypeMapper.selectByExample(bpTypeExample);
	}
	public List<Department> getDepartList(String database){
		Utils.setDatabase(database);
		return departmentMapper.selectByExample(null);
	}
}
