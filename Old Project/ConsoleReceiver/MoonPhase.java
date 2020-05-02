/**
 * Represents the possible moon phases for the console reciever.
 * @author Sean Robinson
 * @version 16APR2020
 */
public enum MoonPhase {

	/** New Moon. */
	NEWMOON,

	/** Full moon. */
	FULLMOON,

	/** First Quarter.  */
	FIRSTQUARTER,

	/** Last Quarter. */
	LASTQUARTER,

	/** Waxing Crescent. */
	WAXINGCRESCENT,

	/** Wacing Gibbous. */
	WAXINGGIBBOUS,

	/** Waning Crescent. */
	WANINGCRESCENT,

	/** Waning Gibbous. */
	WANINGGIBBOUS;
	
	/**
	 * Returns a random MoonPhase.
	 * @return A random MoonPhase.
	 */
	public static MoonPhase getRandom() {
		return values()[(int) (Math.random() * 8)];
	}
}
