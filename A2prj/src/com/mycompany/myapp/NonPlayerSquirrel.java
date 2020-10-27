package com.mycompany.myapp;

import com.codename1.util.MathUtil;

public class NonPlayerSquirrel extends Squirrel implements IStrategy{
	private String strategy;
	
	
	public NonPlayerSquirrel() {
		super();
		setStrategy("NutStrategy");
	}

	@Override
	public void setStrategy(String strat) {
		double beta;
		if(strat.equals("NutStrategy")){
			strategy = strat;
			int target = this.getLastNut();
			target++;
			if(target == 1) {
				beta = MathUtil.atan2(200 - (double)this.getLocation().getY(), 50 - (double)this.getLocation().getX());
				int betaDegrees = (int) Math.toDegrees(beta);
				this.setHeading(betaDegrees);
			}
			else if(target == 2) {
				beta = MathUtil.atan2(275 - (double)this.getLocation().getY(), 200 - (double)this.getLocation().getX());
				int betaDegrees = (int) Math.toDegrees(beta);
				this.setHeading(betaDegrees);
			}
			else if(target == 3) {
				beta = MathUtil.atan2(500 - (double)this.getLocation().getY(), 500 - (double)this.getLocation().getX());
				int betaDegrees = (int) Math.toDegrees(beta);
				this.setHeading(betaDegrees);
			}
			else if(target == 4) {
				beta = MathUtil.atan2(750 - (double)this.getLocation().getY(), 750 - (double)this.getLocation().getX());
				int betaDegrees = (int) Math.toDegrees(beta);
				this.setHeading(betaDegrees);
			}
		}
		else if(strat.equals("PlayerStrategy")){
			strategy = strat;
			int targetA = (int)PlayerSquirrel.getPlayerSquirrel().getLocation().getX();
			int targetB = (int)PlayerSquirrel.getPlayerSquirrel().getLocation().getY();
			beta = MathUtil.atan2(targetB - this.getLocation().getY(), targetA - this.getLocation().getX());
			int betaDegrees = (int) Math.toDegrees(beta);
			this.setHeading(betaDegrees);
		}
		else {
			System.out.println("No valid strategy chosen");
		}
	}

	@Override
	public void invokeStrategy() {
		
		if(strategy.equals("NutStrategy")) {
			
		}
		
	}
	
	@Override
	public String toString() {
		return "NPSquirrel: loc=" + this.getLocation().getX() + ", " + this.getLocation().getY() + " color=" + this.printColor() + " heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed=" + this.getMaximumSpeed() + " steeringDirection=" + this.getSteeringDirection() + " energyLevel=" + this.getEnergyLevel() + " damageLevel=" + this.getDamageLevel() + " Strategy: " + strategy;
	}
	
}
