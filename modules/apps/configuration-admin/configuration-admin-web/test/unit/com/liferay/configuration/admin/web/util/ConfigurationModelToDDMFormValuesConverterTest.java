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

package com.liferay.configuration.admin.web.util;

import com.liferay.configuration.admin.api.ExtendedAttributeDefinition;
import com.liferay.configuration.admin.api.ExtendedObjectClassDefinition;
import com.liferay.configuration.admin.web.model.ConfigurationModel;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.osgi.service.cm.Configuration;

/**
 * @author Marcellus Tavares
 */
public class ConfigurationModelToDDMFormValuesConverterTest extends Mockito {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void
		testGetValuesByConfigurationAndNegativeCardinalityWithTextField() {

		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition attributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {attributeDefinition});

		whenGetCardinality(attributeDefinition, -2);
		whenGetDefaultValue(attributeDefinition, null);
		whenGetID(attributeDefinition, "Text");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		Vector<String> vector = new Vector<>();

		vector.add("Joe Bloggs");
		vector.add("Ella Fitzgerald");

		properties.put("Text", vector);

		whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, configuration, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(2, ddmFormFieldValues.size());
		Assert.assertEquals(
			"Joe Bloggs", getValueString(ddmFormFieldValues.get(0)));
		Assert.assertEquals(
			"Ella Fitzgerald", getValueString(ddmFormFieldValues.get(1)));
	}

	@Test
	public void
		testGetValuesByConfigurationAndPositiveCardinalityWithTextField() {

		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 2);
		whenGetDefaultValue(extendedAttributeDefinition, null);
		whenGetID(extendedAttributeDefinition, "Text");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("Text", new String[] {"Joe Bloggs", "Ella Fitzgerald"});

		whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, configuration, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(2, ddmFormFieldValues.size());
		Assert.assertEquals(
			"Joe Bloggs", getValueString(ddmFormFieldValues.get(0)));
		Assert.assertEquals(
			"Ella Fitzgerald", getValueString(ddmFormFieldValues.get(1)));
	}

	@Test
	public void testGetValuesByConfigurationWithCheckboxField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition extendedAttributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {extendedAttributeDefinition});

		whenGetCardinality(extendedAttributeDefinition, 0);
		whenGetID(extendedAttributeDefinition, "Boolean");

		Configuration configuration = mock(Configuration.class);

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("Boolean", Boolean.TRUE);

		whenGetProperties(configuration, properties);

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, configuration, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(1, ddmFormFieldValues.size());
		Assert.assertEquals("true", getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByDefaultValueWithCheckboxField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition attributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {attributeDefinition});

		whenGetCardinality(attributeDefinition, 0);
		whenGetID(attributeDefinition, "Boolean");

		whenGetDefaultValue(attributeDefinition, new String[] {"false"});

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, null, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(1, ddmFormFieldValues.size());
		Assert.assertEquals("false", getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByDefaultValueWithSelectField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition attributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {attributeDefinition});

		whenGetCardinality(attributeDefinition, 0);
		whenGetDefaultValue(
			attributeDefinition, new String[] {"REQUEST_HEADER"});
		whenGetID(attributeDefinition, "Select");
		whenGetOptionLabels(
			attributeDefinition, new String[] {"COOKIE", "REQUEST_HEADER"});
		whenGetOptionValues(
			attributeDefinition, new String[] {"COOKIE", "REQUEST_HEADER"});

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, null, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(1, ddmFormFieldValues.size());
		Assert.assertEquals(
			"[\"REQUEST_HEADER\"]", getValueString(ddmFormFieldValues.get(0)));
	}

	@Test
	public void testGetValuesByDefaultValueWithTextField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition attributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {attributeDefinition});

		whenGetCardinality(attributeDefinition, 2);
		whenGetDefaultValue(
			attributeDefinition, new String[] {"Joe Bloggs|Ella Fitzgerald"});
		whenGetID(attributeDefinition, "Text");

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, null, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(2, ddmFormFieldValues.size());
		Assert.assertEquals(
			"Joe Bloggs", getValueString(ddmFormFieldValues.get(0)));
		Assert.assertEquals(
			"Ella Fitzgerald", getValueString(ddmFormFieldValues.get(1)));
	}

	@Test
	public void testGetValuesByEmptyDefaultValueWithTextField() {
		ExtendedObjectClassDefinition extendedObjectClassDefinition = mock(
			ExtendedObjectClassDefinition.class);

		ExtendedAttributeDefinition attributeDefinition = mock(
			ExtendedAttributeDefinition.class);

		whenGetAttributeDefinitions(
			extendedObjectClassDefinition,
			new ExtendedAttributeDefinition[] {attributeDefinition});

		whenGetCardinality(attributeDefinition, 0);
		whenGetDefaultValue(attributeDefinition, null);
		whenGetID(attributeDefinition, "Text");

		ConfigurationModel configurationModel = new ConfigurationModel(
			extendedObjectClassDefinition, null, null, false);

		DDMFormValues ddmFormValues = getDDMFormValues(
			configurationModel, getDDMForm(configurationModel));

		List<DDMFormFieldValue> ddmFormFieldValues =
			ddmFormValues.getDDMFormFieldValues();

		Assert.assertEquals(1, ddmFormFieldValues.size());
		Assert.assertEquals(
			StringPool.BLANK, getValueString(ddmFormFieldValues.get(0)));
	}

	protected DDMForm getDDMForm(ConfigurationModel configurationModel) {
		ConfigurationModelToDDMFormConverter
			configurationModelToDDMFormConverter =
				new ConfigurationModelToDDMFormConverter(
					configurationModel, _enLocale);

		return configurationModelToDDMFormConverter.getDDMForm();
	}

	protected DDMFormValues getDDMFormValues(
		ConfigurationModel configurationModel, DDMForm ddmForm) {

		ConfigurationModelToDDMFormValuesConverter
			configurationModelToDDMFormValuesConverter =
				new ConfigurationModelToDDMFormValuesConverter(
					configurationModel, ddmForm, _enLocale);

		return configurationModelToDDMFormValuesConverter.getDDMFormValues();
	}

	protected String getValueString(DDMFormFieldValue ddmFormFieldValue) {
		Value value = ddmFormFieldValue.getValue();

		return value.getString(_enLocale);
	}

	protected void whenGetAttributeDefinitions(
		ExtendedObjectClassDefinition objectClassDefinition,
		ExtendedAttributeDefinition[] returnAttributeDefinitions) {

		when(
			objectClassDefinition.getAttributeDefinitions(Matchers.anyInt())
		).thenReturn(
			returnAttributeDefinitions
		);
	}

	protected void whenGetCardinality(
		ExtendedAttributeDefinition attributeDefinition,
		int returnCardinality) {

		when(
			attributeDefinition.getCardinality()
		).thenReturn(
			returnCardinality
		);
	}

	protected void whenGetDefaultValue(
		ExtendedAttributeDefinition attributeDefinition,
		String[] returnDefaultValue) {

		when(
			attributeDefinition.getDefaultValue()
		).thenReturn(
			returnDefaultValue
		);
	}

	protected void whenGetID(
		ExtendedAttributeDefinition attributeDefinition, String returnID) {

		when(
			attributeDefinition.getID()
		).thenReturn(
			returnID
		);
	}

	protected void whenGetOptionLabels(
		ExtendedAttributeDefinition attributeDefinition,
		String[] returnOptionLabels) {

		when(
			attributeDefinition.getOptionLabels()
		).thenReturn(
			returnOptionLabels
		);
	}

	protected void whenGetOptionValues(
		ExtendedAttributeDefinition attributeDefinition,
		String[] returnOptionValues) {

		when(
			attributeDefinition.getOptionValues()
		).thenReturn(
			returnOptionValues
		);
	}

	protected void whenGetProperties(
		Configuration configuration,
		Dictionary<String, Object> returnProperties) {

		when(
			configuration.getProperties()
		).thenReturn(
			returnProperties
		);
	}

	private final Locale _enLocale = LocaleUtil.US;

}