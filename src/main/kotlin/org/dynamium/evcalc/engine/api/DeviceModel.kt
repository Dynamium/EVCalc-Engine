@file:Suppress("unused")

package org.dynamium.evcalc.engine.api

enum class DeviceModel {
	
	/*
	 * Universal
	 */
	
	EUC_UNIVERSAL,          // Universal one for EUCs (Electric Unicycles)
	ES_UNIVERSAL,           // Universal one for ESs (Electric Scooters)
	
	/*
	 * EUC (Electric Unicycle) Models
	 */
	
	// KingSong
	KINGSONG_KS14M,         // KingSong KS-14M
	KINGSONG_KS14D,         // KingSong KS-14D
	KINGSONG_KS16S,         // KingSong KS-16S
	KINGSONG_KS16X,         // KingSong KS-16X
	KINGSONG_KS18L,         // KingSong KS-18L
	KINGSONG_KS18XL,        // KingSong KS-18XL
	KINGSONG_KSS18,         // KingSong KS-S18
	
	// Begode(GotWay)
	GOTWAY_MTEN,            // GotWay Mten
	GOTWAY_MCM5,            // GotWay MCM 5
	GOTWAY_TESLA,           // GotWay Tesla
	GOTWAY_NIKOLA,          // GotWay Nikola
	GOTWAY_NIKOLA_PLUS,     // GotWay Nikola Plus
	GOTWAY_MSUPER_X_84,     // GotWay MSuper X 84V
	GOTWAY_MSUPER_X_100,    // GotWay MSuper X 100V
	GOTWAY_MSUPER_PRO,      // GotWay MSuper Pro
	GOTWAY_MONSTER_V3,      // GotWay Monster V3
	BEGODE_MONSTER_PRO,     // Begode Monster Pro
	BEGODE_EX,              // Begode EX
	
	// InMotion
	INMOTION_V5,            // InMotion V5
	INMOTION_V8,            // InMotion V8
	INMOTION_V10,           // InMotion V10
	INMOTION_V11,           // InMotion V11
	
	// Ninebot
	NINEBOT_ONE_E,          // Ninebot One E
	NINEBOT_ONE_Z,          // Ninebot One Z
	/*
	 * ES (Electric Scooter) Models
	 */

	// Xiaomi
	XIAOMI_MIJIA_M365,		// Xiaomi Mijia M365

	// Ninebot
	NINEBOT_KICKSCOOTER_MAX	// Ninebot KickScooter Max
}