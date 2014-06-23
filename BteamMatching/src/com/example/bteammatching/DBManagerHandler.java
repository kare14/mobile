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
		val.put("Area", "����Ư����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "15555215554");
		val.put("Name", "������");
		val.put("PW", "123");
		val.put("Area", "����Ư����");
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
		val.put("Area", "��⵵");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "4");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "22222222222");
		val.put("Name", "Ű���");
		val.put("PW", "123");
		val.put("Area", "��⵵");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "3");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "33333333333");
		val.put("Name", "ȫ�浿");
		val.put("PW", "123");
		val.put("Area", "��õ������");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		val.put("Team", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "44444444444");
		val.put("Name", "ö��");
		val.put("PW", "123");
		val.put("Area", "����Ư����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "3");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "55555555555");
		val.put("Name", "����");
		val.put("PW", "123");
		val.put("Area", "����Ư����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "0");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "66666666666");
		val.put("Name", "������");
		val.put("PW", "123");
		val.put("Area", "�뱸������");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "77777777777");
		val.put("Name", "������");
		val.put("PW", "123");
		val.put("Area", "�λ걤����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "4");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "88888888888");
		val.put("Name", "���ٶ˲�");
		val.put("PW", "123");
		val.put("Area", "��걤����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "2");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "99999999999");
		val.put("Name", "����̵�");
		val.put("PW", "123");
		val.put("Area", "������");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "3");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "00000000000");
		val.put("Name", "�Ӳ���");
		val.put("PW", "123");
		val.put("Area", "����Ư����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Phone", "12345678910");
		val.put("Name", "KIDSBEAR");
		val.put("PW", "123");
		val.put("Area", "����Ư����");
		val.put("Height", "175");
		val.put("Weight", "65");
		val.put("Position", "1");
		insert("player", val);
		close();
		val.clear();
		
		val.put("Name", "123");
		val.put("Area", "����Ư����");
		val.put("player1", "0");
		val.put("player2", "1");
		val.put("player3", "2");
		val.put("player4", "3");
		val.put("player5", "4");
		insert("team", val);
		close();
		val.clear();
	}

	// �ݱ�
	public void close() {
		db.close();
	}

	// ����
	public long insert(String table, ContentValues val) {
		db = mDBManager.getWritableDatabase();
		long id=db.insert(table, null, val);
		return id;
	}

	// ��������
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
 * ������Ʈ��� public int update(String table, ContentValues values, String
 * whereClause, String[] whereArgs) {}
 * 
 * ������� public int delete(String table, String whereClause, String[] whereArgs)
 * {}
 */
