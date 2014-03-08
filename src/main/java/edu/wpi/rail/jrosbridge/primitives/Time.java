package edu.wpi.rail.jrosbridge.primitives;

import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;

import edu.wpi.rail.jrosbridge.messages.Message;

public class Time extends Primitive {

	/**
	 * The name of the seconds field for the message.
	 */
	public static final String FIELD_SECS = "secs";
	/**
	 * The name of the nanoseconds field for the message.
	 */
	public static final String FIELD_NSECS = "nsecs";

	/**
	 * The primitive type.
	 */
	public static final String TYPE = "time";

	private int secs, nsecs;

	/**
	 * Create a new Time with a default of 0.
	 */
	public Time() {
		this(0, 0);
	}

	/**
	 * Create a new Time with the given seconds value (nsecs will be set to 0).
	 * 
	 * @param secs
	 *            The seconds value of this time.
	 */
	public Time(int secs) {
		this(secs, 0);
	}

	/**
	 * Create a new Time with the given seconds and nanoseconds values.
	 * 
	 * @param secs
	 *            The seconds value of this time.
	 * @param nsecs
	 *            The nanoseconds value of this time.
	 */
	public Time(int secs, int nsecs) {
		// build the JSON object
		super(Json.createObjectBuilder().add(Time.FIELD_SECS, secs)
				.add(Time.FIELD_NSECS, nsecs).build(), Time.TYPE);
		this.secs = secs;
		this.nsecs = nsecs;
	}

	/**
	 * Get the seconds value of this time.
	 * 
	 * @return The seconds value of this time.
	 */
	public int getSecs() {
		return this.secs;
	}

	/**
	 * Get the nanoseconds value of this time.
	 * 
	 * @return The nanoseconds value of this time.
	 */
	public int getNsecs() {
		return this.secs;
	}

	/**
	 * Create a clone of this Time.
	 */
	@Override
	public Time clone() {
		return new Time(this.secs, this.nsecs);
	}

	/**
	 * Crate a new Java Date object based on this message.
	 * 
	 * @return A new Java Date object based on this message.
	 */
	public Date toDate() {
		// TODO
		return null;
	}

	/**
	 * Create a new Time message based on the current system time.
	 * 
	 * @return The new Time message.
	 */
	public static Time now() {
		return Time.fromNanoTime(System.nanoTime());
	}

	/**
	 * Create a new Time message based on the given nanoseconds time.
	 * 
	 * @param nanoTime
	 *            The time to create a message from in nanoseconds.
	 * 
	 * @return The new Time message.
	 */
	public static Time fromNanoTime(long nanoTime) {
		double conversion = (double) nanoTime / 1000000000.0;

		// extract seconds and nanoseconds
		int secs = (int) conversion;
		int nsecs = (int) ((conversion - secs) * 1000000000.0);
		return new Time(secs, nsecs);
	}

	/**
	 * Create a new Time based on the given JSON string. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonString
	 *            The JSON string to parse.
	 * @return A Time message based on the given JSON string.
	 */
	public static Time fromJsonString(String jsonString) {
		// convert to a message
		return Time.fromMessage(new Message(jsonString));
	}

	/**
	 * Create a new Time based on the given Message. Any missing values will be
	 * set to their defaults.
	 * 
	 * @param m
	 *            The Message to parse.
	 * @return A Time message based on the given Message.
	 */
	public static Time fromMessage(Message m) {
		// get it from the JSON object
		return Time.fromJsonObject(m.toJsonObject());
	}

	/**
	 * Create a new Time based on the given JSON object. Any missing values will
	 * be set to their defaults.
	 * 
	 * @param jsonObject
	 *            The JSON object to parse.
	 * @return A Time message based on the given JSON object.
	 */
	public static Time fromJsonObject(JsonObject jsonObject) {
		// check the fields
		int secs = jsonObject.containsKey(Time.FIELD_SECS) ? jsonObject
				.getInt(Time.FIELD_SECS) : 0;
		int nsecs = jsonObject.containsKey(Time.FIELD_NSECS) ? jsonObject
				.getInt(Time.FIELD_NSECS) : 0;
		return new Time(secs, nsecs);
	}

}
