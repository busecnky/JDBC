package com.january2.factory;


public class EnemyFactorySingleton {

	
	private static EnemyFactory enemyFactory;
	
	public static EnemyFactory enemyFactory(){
		if(enemyFactory == null) {
			enemyFactory = new EnemyFactory();
		}
		return enemyFactory;
	}
	
	
}
