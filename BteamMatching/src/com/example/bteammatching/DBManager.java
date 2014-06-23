package com.example.bteammatching;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB ���� �� ���׷��̵带 �����ִ� ����� Ŭ���� �����
 *  - SQLiteOpenHelper �߻� Ŭ������ ��ӹ޾Ƽ� �����. - 
 */

public class DBManager extends SQLiteOpenHelper {

	public static CursorFactory factory = null;
	public static int version = 1;

	public DBManager(Context context) {

		super(context, "btm.db", factory, version);

	}

	// DB�� ������ �� ȣ��Ǵ� �޼ҵ�

	@Override
	public void onCreate(SQLiteDatabase db) {

		// TODO Auto-generated method stub

		// DB�� ���̺� �����ϱ�

		String sql = "CREATE TABLE player"
				+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT" + "," + "Phone TEXT"
				+ "," + "Name TEXT" + "," + "PW TEXT" + "," + "Area TEXT" + ","
				+ "Height TEXT" + "," + "Weight TEXT" + "," + "Position TEXT"
				+ "," + "WP TEXT" + "," + "SP TEXT" +"," + "Team TEXT"+ ");";

		db.execSQL(sql);
			
		sql = "CREATE TABLE team" + "(_id INTEGER PRIMARY KEY AUTOINCREMENT"
				+ "," + "Name TEXT" + "," + "Area TEXT" + ","
				+ "GameNumber TEXT" + "," + "Win TEXT" + "," + "Lose TEXT"
				+ "," + "player1 TEXT" + "," + "player2 TEXT" + ","
				+ "player3 TEXT" + "," + "player4 TEXT" + "," + "player5 TEXT"
				+ ");";

		// sql�� �����ϱ�

		db.execSQL(sql);


	}

	// DB�� ���� ���� ���� ���� �ʿ䰡 ���� �� ȣ��Ǵ� �޼ҵ�

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXSITS member");

		// ���� ������ �� �ֵ��� onCreate() �޼ҵ带 �����Ѵ�.

		onCreate(db);

	}

}
