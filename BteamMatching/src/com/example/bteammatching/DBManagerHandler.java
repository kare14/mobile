package com.example.bteammatching;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManagerHandler {
	private DBManager mDBManager;
	private SQLiteDatabase db;

	public DBManagerHandler(Context context) {
		this.mDBManager = new DBManager(context);
	}

	public void createTest() {

		ContentValues val = new ContentValues();
		val.put("Phone", "01034773219");
		val.put("Name", "KID");
		val.put("PW", "123");
		val.put("Area", "서울특별시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "15555215554");
		val.put("Name", "나도야");
		val.put("PW", "123");
		val.put("Area", "서울특별시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "2");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();

		val.put("Phone", "11111111111");
		val.put("Name", "KIDS");
		val.put("PW", "123");
		val.put("Area", "경기도");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "4");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "22222222222");
		val.put("Name", "키즈베어");
		val.put("PW", "123");
		val.put("Area", "경기도");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "3");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "33333333333");
		val.put("Name", "홍길동");
		val.put("PW", "123");
		val.put("Area", "인천광역시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "44444444444");
		val.put("Name", "철수");
		val.put("PW", "123");
		val.put("Area", "서울특별시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "3");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "55555555555");
		val.put("Name", "영희");
		val.put("PW", "123");
		val.put("Area", "서울특별시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "0");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "66666666666");
		val.put("Name", "갑돌이");
		val.put("PW", "123");
		val.put("Area", "대구광역시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "77777777777");
		val.put("Name", "갑순이");
		val.put("PW", "123");
		val.put("Area", "부산광역시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "4");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "88888888888");
		val.put("Name", "빵꾸똥꾸");
		val.put("PW", "123");
		val.put("Area", "울산광역시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "2");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "99999999999");
		val.put("Name", "드로이드");
		val.put("PW", "123");
		val.put("Area", "강원도");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "3");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "00000000000");
		val.put("Name", "임꺽정");
		val.put("PW", "123");
		val.put("Area", "서울특별시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "12345678910");
		val.put("Name", "KIDSBEAR");
		val.put("PW", "123");
		val.put("Area", "서울특별시");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Name", "123");
		val.put("Area", "서울특별시");
		val.put("player1", "0");
		val.put("player2", "1");
		val.put("player3", "2");
		val.put("player4", "3");
		val.put("player5", "4");
		insert("team", val);
		close();
		val.clear();
	}

	// 닫기
	public void close() {
		db.close();
	}

	// 저장
	public long insert(String table, ContentValues val) {
		db = mDBManager.getWritableDatabase();
		long id=db.insert(table, null, val);
		return id;
	}

	// 가저오기
	public Cursor select(String table, String selection, String[] selectionArg) {
		db = mDBManager.getReadableDatabase();
		Cursor cursor = db.query(table, null, selection, selectionArg, null,
				null, null, null);
		return cursor;
	}

	public Cursor selectAll(String table) {
		db = mDBManager.getReadableDatabase();
		Cursor cursor = db.query(table, null, null, null, null, null, null);
		return cursor;
	}

	public void update(String table, ContentValues val, String sel,String arg) {
		db = mDBManager.getWritableDatabase();
		db.update(table, val, sel+"= ?", new String[] { arg });
	}

	public Cursor query(String query, String rw) {
		if (rw.equals("r"))
			db = mDBManager.getReadableDatabase();
		else
			db = mDBManager.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);
		return cursor;
	}

}

/**
 * 업데이트방법 public int update(String table, ContentValues values, String
 * whereClause, String[] whereArgs) {}
 * 
 * 삭제방법 public int delete(String table, String whereClause, String[] whereArgs)
 * {}
 */
