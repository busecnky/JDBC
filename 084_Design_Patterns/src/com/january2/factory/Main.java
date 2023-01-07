package com.january2.factory;

public class Main {

	
	public static void main(String[] args) {
		
		EnemyFactory enemyFactory = new EnemyFactory();
		enemyFactory.createEnemy(IEnemy.ARCHER).attack();
		enemyFactory.createEnemy(IEnemy.CAVALRY).attack();
		enemyFactory.createEnemy(IEnemy.INFANTRY).attack();

	
		//enemyFactory.createEnemy(null).attack();
		//enemyFactory.createEnemy(IEnemy.empty).attack();
		
		
		
		
		EnemyFactorySingleton.enemyFactory().createEnemy(IEnemy.ARCHER).attack();
		
	}
}
