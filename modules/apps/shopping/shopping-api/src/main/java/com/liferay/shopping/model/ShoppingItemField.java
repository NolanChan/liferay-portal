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

package com.liferay.shopping.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the ShoppingItemField service. Represents a row in the &quot;ShoppingItemField&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingItemFieldModel
 * @see com.liferay.shopping.model.impl.ShoppingItemFieldImpl
 * @see com.liferay.shopping.model.impl.ShoppingItemFieldModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.shopping.model.impl.ShoppingItemFieldImpl")
@ProviderType
public interface ShoppingItemField extends ShoppingItemFieldModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.shopping.model.impl.ShoppingItemFieldImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ShoppingItemField, Long> ITEM_FIELD_ID_ACCESSOR =
		new Accessor<ShoppingItemField, Long>() {
			@Override
			public Long get(ShoppingItemField shoppingItemField) {
				return shoppingItemField.getItemFieldId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ShoppingItemField> getTypeClass() {
				return ShoppingItemField.class;
			}
		};

	public java.lang.String[] getValuesArray();

	public void setValuesArray(java.lang.String[] valuesArray);
}