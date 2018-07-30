package com.syn.dao;
import java.util.List;

import com.syn.entities.TestProcTable;


public interface TestProcTableDao {
	public void showProcUsers(List userList);
	public List getAllProcUsers();
	public boolean addProcUser(TestProcTable procUser);
}
