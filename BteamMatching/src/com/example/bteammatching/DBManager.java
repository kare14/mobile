package com.example.bteammatching;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * DB 생성 및 업그레이드를 도와주는 도우미 클래스 만들기
 *  - SQLiteOpenHelper 추상 클래스를 상속받아서 만든다. - 
 */

public class DBManager extends SQLiteOpenHelper {

	public static CursorFactory factory = null;
	public static int version = 1;

	public DBManager(Context context) {

		super(context, "btm.db", factory, version);

	}

	// DB가 생성될 때 호출되는 메소드

	@Override
	public void onCreate(SQLiteDatabase db) {

		// TODO Auto-generated method stub

		// DB에 테이블 생성하기

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

		// sql문 실행하기

		db.execSQL(sql);


	}

	// DB를 갈아 엎고 새로 만들 필요가 있을 때 호출되는 메소드

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXSITS member");

		// 새로 생성될 수 있도록 onCreate() 메소드를 생성한다.

		onCreate(db);

	}

}
