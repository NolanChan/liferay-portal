package ${packagePath}.model.impl;

import ${packagePath}.model.${entity.name};

<#if entity.hasLocalService() && entity.hasColumns()>
	import ${packagePath}.service.${entity.name}LocalServiceUtil;

	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.exception.SystemException;
	import com.liferay.portal.kernel.util.StringBundler;
	import com.liferay.portal.kernel.util.StringPool;
	import com.liferay.portal.model.TreeModel;

	import java.util.ArrayList;
	import java.util.List;
</#if>

/**
 * The extended model base implementation for the ${entity.name} service. Represents a row in the &quot;${entity.table}&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ${entity.name}Impl}.
 * </p>
 *
 * @author ${author}
 * @see ${entity.name}Impl
 * @see ${packagePath}.model.${entity.name}
 * @generated
 */
public abstract class ${entity.name}BaseImpl extends ${entity.name}ModelImpl implements ${entity.name} {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ${entity.humanName} model instance should use the {@link ${entity.name}} interface instead.
	 */

	<#if entity.hasLocalService() && entity.hasColumns()>
		@Override
		public void persist() {
			if (this.isNew()) {
				${entity.name}LocalServiceUtil.add${entity.name}(this);
			}
			else {
				${entity.name}LocalServiceUtil.update${entity.name}(this);
			}
		}

		<#if entity.isTreeModel()>
			<#assign pkColumn = entity.getPKList()?first>

			<#if entity.hasColumn("parent" + pkColumn.methodName)>
				@Override
				@SuppressWarnings("unused")
				public String buildTreePath() throws PortalException {
					List<${entity.name}> ${entity.varNames} = new ArrayList<${entity.name}>();

					${entity.name} ${entity.varName} = this;

					while (${entity.varName} != null) {
						${entity.varNames}.add(${entity.varName});

						${entity.varName} = ${entity.name}LocalServiceUtil.fetch${entity.name}(${entity.varName}.getParent${pkColumn.methodName}());
					}

					StringBundler sb = new StringBundler(${entity.varNames}.size() * 2 + 1);

					sb.append(StringPool.SLASH);

					for (int i = ${entity.varNames}.size() - 1; i >= 0; i--) {
						${entity.varName} = ${entity.varNames}.get(i);

						sb.append(${entity.varName}.get${entity.PKList[0].methodName}());
						sb.append(StringPool.SLASH);
					}

					return sb.toString();
				}
			</#if>

			@Override
			public void updateTreePath(String treePath) {
				${entity.name} ${entity.varName} = this;

				${entity.varName}.setTreePath(treePath);

				${entity.name}LocalServiceUtil.update${entity.name}(${entity.varName});
			}
		</#if>
	</#if>

}