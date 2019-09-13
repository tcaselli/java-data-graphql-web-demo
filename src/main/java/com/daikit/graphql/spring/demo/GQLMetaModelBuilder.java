package com.daikit.graphql.spring.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;

import com.daikit.graphql.custommethod.IGQLAbstractCustomMethod;
import com.daikit.graphql.dynamicattribute.IGQLAbstractDynamicAttribute;
import com.daikit.graphql.enums.GQLScalarTypeEnum;
import com.daikit.graphql.meta.GQLMetaModel;
import com.daikit.graphql.meta.attribute.GQLAttributeEntityMetaData;
import com.daikit.graphql.meta.attribute.GQLAttributeEnumMetaData;
import com.daikit.graphql.meta.attribute.GQLAttributeListEntityMetaData;
import com.daikit.graphql.meta.attribute.GQLAttributeListEnumMetaData;
import com.daikit.graphql.meta.attribute.GQLAttributeListScalarMetaData;
import com.daikit.graphql.meta.attribute.GQLAttributeScalarMetaData;
import com.daikit.graphql.meta.entity.GQLEntityMetaData;
import com.daikit.graphql.meta.entity.GQLEnumMetaData;
import com.daikit.graphql.spring.demo.data.AbstractEntity;
import com.daikit.graphql.spring.demo.data.EmbeddedData1;
import com.daikit.graphql.spring.demo.data.EmbeddedData2;
import com.daikit.graphql.spring.demo.data.EmbeddedData3;
import com.daikit.graphql.spring.demo.data.Entity1;
import com.daikit.graphql.spring.demo.data.Entity2;
import com.daikit.graphql.spring.demo.data.Entity3;
import com.daikit.graphql.spring.demo.data.Entity4;
import com.daikit.graphql.spring.demo.data.Entity5;
import com.daikit.graphql.spring.demo.data.Entity6;
import com.daikit.graphql.spring.demo.data.Enum1;

/**
 * Meta data for building the schema.
 *
 * @author Thibaut Caselli
 */
public class GQLMetaModelBuilder {

	/**
	 * Build the test GraphQL data meta entity
	 *
	 * @param applicationContext
	 *            the Spring {@link ApplicationContext}
	 *
	 * @return the built {@link GQLMetaModel}
	 */
	public GQLMetaModel build(ApplicationContext applicationContext) {
		final Collection<GQLEntityMetaData> entityMetaDatas = Arrays.asList(buildEntity1(), buildEntity2(),
				buildEntity3(), buildEntity4(), buildEntity5(), buildEntity6(), buildEmbeddedData1(),
				buildEmbeddedData2(), buildEmbeddedData3());
		final Collection<GQLEnumMetaData> enumMetaDatas = Arrays.asList(buildEnumMetaData());

		final Collection<IGQLAbstractDynamicAttribute<?>> dynamicAttributes = applicationContext
				.getBeansOfType(IGQLAbstractDynamicAttribute.class, false, false).values().stream()
				.map(dynamicAttribute -> (IGQLAbstractDynamicAttribute<?>) dynamicAttribute)
				.collect(Collectors.toList());
		final Collection<IGQLAbstractCustomMethod<?>> customMethods = applicationContext
				.getBeansOfType(IGQLAbstractCustomMethod.class, false, false).values().stream()
				.map(method -> (IGQLAbstractCustomMethod<?>) method).collect(Collectors.toList());

		return new GQLMetaModel(enumMetaDatas, entityMetaDatas, dynamicAttributes, customMethods);
	}

	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	// PRIVATE UTILS
	// *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

	private GQLEntityMetaData buildEntity1() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(Entity1.class.getSimpleName(), Entity1.class,
				AbstractEntity.class);
		entity.addAttribute(new GQLAttributeScalarMetaData("id", GQLScalarTypeEnum.ID).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("intAttr", GQLScalarTypeEnum.INT).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("longAttr", GQLScalarTypeEnum.LONG).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("doubleAttr", GQLScalarTypeEnum.FLOAT).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("stringAttr", GQLScalarTypeEnum.STRING));
		entity.addAttribute(
				new GQLAttributeScalarMetaData("booleanAttr", GQLScalarTypeEnum.BOOLEAN).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("bigIntAttr", GQLScalarTypeEnum.BIG_INTEGER));
		entity.addAttribute(new GQLAttributeScalarMetaData("bigDecimalAttr", GQLScalarTypeEnum.BIG_DECIMAL));
		entity.addAttribute(new GQLAttributeScalarMetaData("bytesAttr", GQLScalarTypeEnum.BYTE));

		final GQLAttributeScalarMetaData shortAttr = new GQLAttributeScalarMetaData("shortAttr",
				GQLScalarTypeEnum.SHORT);
		shortAttr.setNullable(false);
		entity.addAttribute(shortAttr);

		entity.addAttribute(new GQLAttributeScalarMetaData("charAttr", GQLScalarTypeEnum.CHAR));
		entity.addAttribute(new GQLAttributeScalarMetaData("dateAttr", GQLScalarTypeEnum.DATE));
		entity.addAttribute(new GQLAttributeScalarMetaData("fileAttr", GQLScalarTypeEnum.FILE));
		entity.addAttribute(new GQLAttributeScalarMetaData("localDateAttr", GQLScalarTypeEnum.LOCAL_DATE));
		entity.addAttribute(new GQLAttributeScalarMetaData("localDateTimeAttr", GQLScalarTypeEnum.LOCAL_DATE_TIME));
		entity.addAttribute(new GQLAttributeListScalarMetaData("stringList", GQLScalarTypeEnum.STRING));
		entity.addAttribute(new GQLAttributeListScalarMetaData("stringSet", GQLScalarTypeEnum.STRING));
		entity.addAttribute(new GQLAttributeEnumMetaData("enumAttr", Enum1.class));
		entity.addAttribute(new GQLAttributeListEnumMetaData("enumList", Enum1.class));
		entity.addAttribute(new GQLAttributeListEnumMetaData("enumSet", Enum1.class));
		entity.addAttribute(new GQLAttributeEntityMetaData("entity2", Entity2.class));
		entity.addAttribute(new GQLAttributeListEntityMetaData("entity3s", Entity3.class));
		entity.addAttribute(new GQLAttributeListEntityMetaData("entity4s", Entity4.class));
		entity.addAttribute(new GQLAttributeEntityMetaData("embeddedData1", EmbeddedData1.class).setEmbedded(true));
		entity.addAttribute(
				new GQLAttributeListEntityMetaData("embeddedData1s", EmbeddedData1.class).setEmbedded(true));

		return entity;
	}

	private GQLEntityMetaData buildEntity2() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(Entity2.class.getSimpleName(), Entity2.class,
				AbstractEntity.class);
		entity.addAttribute(new GQLAttributeScalarMetaData("id", GQLScalarTypeEnum.ID).setNullable(false));
		entity.addAttribute(new GQLAttributeListEntityMetaData("entity1s", Entity1.class));
		return entity;
	}

	private GQLEntityMetaData buildEntity3() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(Entity3.class.getSimpleName(), Entity3.class,
				AbstractEntity.class);
		entity.addAttribute(new GQLAttributeScalarMetaData("id", GQLScalarTypeEnum.ID).setNullable(false));
		entity.addAttribute(new GQLAttributeEntityMetaData("entity1", Entity1.class));
		return entity;
	}

	private GQLEntityMetaData buildEntity4() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(Entity4.class.getSimpleName(), Entity4.class,
				AbstractEntity.class);
		entity.addAttribute(new GQLAttributeScalarMetaData("id", GQLScalarTypeEnum.ID).setNullable(false));
		entity.addAttribute(new GQLAttributeListEntityMetaData("entity1s", Entity1.class));
		return entity;
	}

	private GQLEntityMetaData buildEntity5() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(Entity5.class.getSimpleName(), Entity5.class,
				AbstractEntity.class);
		entity.addAttribute(new GQLAttributeScalarMetaData("id", GQLScalarTypeEnum.ID).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("intAttr", GQLScalarTypeEnum.INT));
		entity.addAttribute(new GQLAttributeScalarMetaData("stringAttr", GQLScalarTypeEnum.STRING));
		return entity;
	}

	private GQLEntityMetaData buildEntity6() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(Entity6.class.getSimpleName(), Entity6.class,
				AbstractEntity.class);
		entity.addAttribute(new GQLAttributeScalarMetaData("id", GQLScalarTypeEnum.ID).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("attr1", GQLScalarTypeEnum.STRING).setReadable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("attr2", GQLScalarTypeEnum.STRING).setSaveable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("attr3", GQLScalarTypeEnum.STRING).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("attr4", GQLScalarTypeEnum.STRING).setFilterable(false));
		return entity;
	}

	private GQLEntityMetaData buildEmbeddedData1() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(EmbeddedData1.class.getSimpleName(), EmbeddedData1.class)
				.setEmbedded(true);

		entity.addAttribute(new GQLAttributeScalarMetaData("intAttr", GQLScalarTypeEnum.INT).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("longAttr", GQLScalarTypeEnum.LONG).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("doubleAttr", GQLScalarTypeEnum.FLOAT).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("stringAttr", GQLScalarTypeEnum.STRING));
		entity.addAttribute(
				new GQLAttributeScalarMetaData("booleanAttr", GQLScalarTypeEnum.BOOLEAN).setNullable(false));
		entity.addAttribute(new GQLAttributeScalarMetaData("bigIntAttr", GQLScalarTypeEnum.BIG_INTEGER));
		entity.addAttribute(new GQLAttributeScalarMetaData("bigDecimalAttr", GQLScalarTypeEnum.BIG_DECIMAL));
		entity.addAttribute(new GQLAttributeScalarMetaData("bytesAttr", GQLScalarTypeEnum.BYTE));

		final GQLAttributeScalarMetaData shortAttr = new GQLAttributeScalarMetaData("shortAttr",
				GQLScalarTypeEnum.SHORT);
		shortAttr.setNullable(false);
		entity.addAttribute(shortAttr);

		entity.addAttribute(new GQLAttributeScalarMetaData("charAttr", GQLScalarTypeEnum.CHAR));
		entity.addAttribute(new GQLAttributeScalarMetaData("dateAttr", GQLScalarTypeEnum.DATE));
		entity.addAttribute(new GQLAttributeScalarMetaData("fileAttr", GQLScalarTypeEnum.FILE));
		entity.addAttribute(new GQLAttributeScalarMetaData("localDateAttr", GQLScalarTypeEnum.LOCAL_DATE));
		entity.addAttribute(new GQLAttributeScalarMetaData("localDateTimeAttr", GQLScalarTypeEnum.LOCAL_DATE_TIME));
		entity.addAttribute(new GQLAttributeListScalarMetaData("stringList", GQLScalarTypeEnum.STRING));
		entity.addAttribute(new GQLAttributeListScalarMetaData("stringSet", GQLScalarTypeEnum.STRING));
		entity.addAttribute(new GQLAttributeEnumMetaData("enumAttr", Enum1.class));
		entity.addAttribute(new GQLAttributeListEnumMetaData("enumList", Enum1.class));
		entity.addAttribute(new GQLAttributeListEnumMetaData("enumSet", Enum1.class));
		entity.addAttribute(new GQLAttributeEntityMetaData("data2", EmbeddedData2.class).setEmbedded(true));
		entity.addAttribute(new GQLAttributeListEntityMetaData("data3s", EmbeddedData3.class).setEmbedded(true));

		return entity;
	}

	private GQLEntityMetaData buildEmbeddedData2() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(EmbeddedData2.class.getSimpleName(), EmbeddedData2.class)
				.setEmbedded(true);
		entity.addAttribute(new GQLAttributeScalarMetaData("intAttr", GQLScalarTypeEnum.INT).setNullable(false));
		return entity;
	}

	private GQLEntityMetaData buildEmbeddedData3() {
		final GQLEntityMetaData entity = new GQLEntityMetaData(EmbeddedData3.class.getSimpleName(), EmbeddedData3.class)
				.setEmbedded(true);
		entity.addAttribute(new GQLAttributeScalarMetaData("intAttr", GQLScalarTypeEnum.INT).setNullable(false));
		return entity;
	}

	private GQLEnumMetaData buildEnumMetaData() {
		return new GQLEnumMetaData(Enum1.class.getSimpleName(), Enum1.class);
	}

}
