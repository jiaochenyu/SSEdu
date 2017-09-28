package com.gsys.common;

import org.apache.ibatis.session.SqlSession;
import org.our.cycle.common.Utils;

/**
 * DbExecutor
 */
public class DbExecutor {

	public static SqlSession open() {
		SqlSession mysession = Utils.getSqlSessionFactory().openSession();
		return mysession;
	}

	public static void close(SqlSession mysession) {
		try {
			if (mysession != null) {
				mysession.close();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public static void rollback(SqlSession mysession) {
		try {
			if (mysession != null) {
				mysession.rollback();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
