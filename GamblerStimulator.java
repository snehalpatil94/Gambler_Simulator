package com.bl.gamblingsimulator;

/*
 *
 * Welcome message. Finding result of game resign stake on win
 * calculating total winning cost total win and lost in a month
 * per day win and lost amount on win play next month
 * 
 * @author : Snehal Patil
 *  */
public class GamblerStimulator {
	public static final double initialStake = 100;
	public static final double stakeBet = 1;
	public static int stake = 0, monthlyWin = 0, getMonthlyLoss = 0;
	public static final int numberOfPlays = 30;
	public static double totalStake = 0;
	public static double maxWin = 0;
	public static double maxLoss = 0;
	public static int maxWinDay, maxLossDay;

	public static boolean calculation() {
		boolean isWinner = false;
		if ((int) Math.floor(Math.random() * 10) % 2 == 1) {
			monthlyWin++;
			stake++;
			isWinner = true;
		} else {
			getMonthlyLoss++;
			stake--;
		}
		return isWinner;
	}

	public static boolean result() {
		boolean resign = false;
		if (stake >= initialStake + (initialStake * 0.5) || stake <= initialStake / 2) {
			resign = true;
			System.out.println(stake);
		}
		return resign;
	}

	public static void calculateStake() {
		totalStake = totalStake + stake;
	}

	public static boolean calculateMaximumWin() {
		if (totalStake > maxWin) {
			maxWin = totalStake;
			return true;
		}
		return false;
	}

	public static boolean calculateMaximumLoss() {
		if (totalStake < initialStake) {
			double loss = initialStake - totalStake;
			if (loss > maxLoss) {
				maxLoss = loss;
				return true;
			}
		}
		return false;

	}

	public static boolean playNextMonth() {
		boolean willPlay = false;
		if (totalStake > (initialStake * numberOfPlays)) {
			willPlay = true;
		}
		return willPlay;
	}

	public static void main(String[] args) {
		boolean playAgain = true;
		while (playAgain) {

			System.out.println("---------♥-Welcome To Gambler Stimulator-♥---------");

			boolean resign = false;
			boolean betWin = false;
			double balance = 0;
			for (int numberOfDays = 0; numberOfDays < numberOfPlays; numberOfDays++) {
				int stake = (int) initialStake;
				while (resign == false) {
					betWin = calculation();
					if (result() == true) {
						calculateStake();
						break;
					}
				}
				if (calculateMaximumWin()) {
					maxWinDay = numberOfDays;
				}
				if (calculateMaximumLoss()) {
					maxLossDay = numberOfDays;
				}
			}
			System.out.println("Total amount with Player after " + numberOfPlays + " days : " + totalStake);

			double totalAmountWinAndLost = (initialStake * numberOfPlays) - balance;
			System.out.println("Number of days won : " + monthlyWin);
			System.out.println("Number of days loss : " + getMonthlyLoss);
			System.out.println("Maximum Amount Won On Day " + maxWinDay);
			System.out.println("Maximum Amount Loss: " + maxLoss);
			System.out.println("Maximum Amount Loss On Day " + maxLossDay);

			System.out.println("Total amount won and lost : " + totalAmountWinAndLost);

			if (playNextMonth()) {
				playAgain = true;
			} else {
				playAgain = false;
			}
		}
	}
}
