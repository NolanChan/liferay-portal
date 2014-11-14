/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Preston Crary
 */
public class UserPasswordException extends PortalException {

	@Deprecated
	public static final int PASSWORD_ALREADY_USED = 1;

	@Deprecated
	public static final int PASSWORD_CONTAINS_TRIVIAL_WORDS = 2;

	@Deprecated
	public static final int PASSWORD_INVALID = 3;

	@Deprecated
	public static final int PASSWORD_LENGTH = 4;

	@Deprecated
	public static final int PASSWORD_NOT_CHANGEABLE = 5;

	@Deprecated
	public static final int PASSWORD_SAME_AS_CURRENT = 6;

	@Deprecated
	public static final int PASSWORD_TOO_TRIVIAL = 8;

	@Deprecated
	public static final int PASSWORD_TOO_YOUNG = 9;

	@Deprecated
	public static final int PASSWORDS_DO_NOT_MATCH = 10;

	/**
	 * @deprecated As of 7.0.0, replaced by the inner classes
	 */
	@Deprecated
	public UserPasswordException(int type) {
		_type = type;
	}

	/**
	 * @deprecated As of 7.0.0, replaced by the inner classes
	 */
	@Deprecated
	public int getType() {
		return _type;
	}

	public static class MustBeLonger extends UserPasswordException {

		public MustBeLonger(long userId, int minLength) {
			super(
				String.format(
					"Password for user %s must be at least %s characters",
					userId, minLength),
				PASSWORD_LENGTH);

			_minLength = minLength;
			_userId = userId;
		}

		public int getMinLength() {
			return _minLength;
		}

		public long getUserId() {
			return _userId;
		}

		private final int _minLength;
		private long _userId;

	}

	public static class MustComplyWithListeners extends UserPasswordException {
		public MustComplyWithListeners(
			long userId, ModelListenerException modelListenerException) {

			super(
				String.format(
					"Password must not make a user model listener fail: " +
						modelListenerException.getMessage()),
				PASSWORD_INVALID);

			_modelListenerException = modelListenerException;
			_userId = userId;
		}

		public ModelListenerException getModelListenerException() {
			return _modelListenerException;
		}

		public long getUserId() {
			return _userId;
		}

		private final ModelListenerException _modelListenerException;
		private long _userId;

	}

	public static class MustComplyWithRegex extends UserPasswordException {
		public MustComplyWithRegex(long userId, String regex) {
			super(
				String.format("Password must comply with regex: " + regex),
				PASSWORD_INVALID);

			_regex = regex;
			_userId = userId;
		}

		public String getRegex() {
			return _regex;
		}

		public long getUserId() {
			return _userId;
		}

		private final String _regex;
		private long _userId;

	}

	public static class MustMatch extends UserPasswordException {

		public MustMatch(long userId) {
			super(
				String.format("Passwords for user %s must match", userId),
				PASSWORDS_DO_NOT_MATCH);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustMatchCurrentPassword extends UserPasswordException {

		public MustMatchCurrentPassword(long userId) {
			super(
				String.format(
					"Password for user %s does not match the current password",
					userId),
				PASSWORD_INVALID);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustNotBeChanged extends UserPasswordException {

		public MustNotBeChanged(long userId) {
			super(
				String.format(
					"Password for user %s must not be changed", userId),
				PASSWORD_NOT_CHANGEABLE);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustNotBeChangedYet extends UserPasswordException {

		public MustNotBeChangedYet(long userId, Date changeableDate) {
			super(
				String.format(
					"Password for user %s must not be changed until %s", userId,
					changeableDate),
				PASSWORD_TOO_YOUNG);

			_userId = userId;
			_changeableDate = changeableDate;
		}

		public Date getChangeableDate() {
			return _changeableDate;
		}

		public long getUserId() {
			return _userId;
		}

		private final Date _changeableDate;
		private long _userId;

	}

	public static class MustNotBeEqualToCurrent extends UserPasswordException {

		public MustNotBeEqualToCurrent(long userId) {
			super(
				String.format(
					"Password for user %s must not be equal to their current " +
						"password",
					userId),
				PASSWORD_SAME_AS_CURRENT);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustNotBeNull extends UserPasswordException {

		public MustNotBeNull(long userId) {
			super(
				String.format("Password for user %s must not be null", userId),
				PASSWORD_INVALID);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustNotBeRecentlyUsed extends UserPasswordException {

		public MustNotBeRecentlyUsed(long userId) {
			super(
				String.format(
					"Password for user %s has been used too recently",
						userId),
				PASSWORD_ALREADY_USED);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustNotBeTrivial extends UserPasswordException {

		public MustNotBeTrivial(long userId) {
			super(
				String.format(
					"Password for user %s must not be too trivial", userId),
				PASSWORD_TOO_TRIVIAL);

			_userId = userId;
		}

		public long getUserId() {
			return _userId;
		}

		private long _userId;

	}

	public static class MustNotContainDictionaryWords
		extends UserPasswordException {

		public MustNotContainDictionaryWords(
			long userId, List<String> dictionaryWords) {

			super(
				String.format(
					"Password for user %s must not contain any dictionary " +
						"words such as: %s",
					userId, _getDictionaryWordsString(dictionaryWords)),
				PASSWORD_CONTAINS_TRIVIAL_WORDS);

			_userId = userId;
			_dictionaryWords = dictionaryWords;
		}

		public List<String> getDictionaryWords() {
			return _dictionaryWords;
		}

		public long getUserId() {
			return _userId;
		}

		private final List<String> _dictionaryWords;
		private long _userId;

	}

	private static String _getDictionaryWordsString(
		List<String> dictionaryWords) {

		if (dictionaryWords.size() <= 10) {
			return dictionaryWords.toString();
		}

		List sampleDictionaryWords = dictionaryWords.subList(0, 10);

		return sampleDictionaryWords.toString() + StringPool.SPACE +
			StringPool.TRIPLE_PERIOD;
	}

	private UserPasswordException(String message, int type) {
		super(message);

		_type = type;
	}

	private final int _type;

}