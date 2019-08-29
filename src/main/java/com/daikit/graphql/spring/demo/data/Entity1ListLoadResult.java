package com.daikit.graphql.spring.demo.data;

import java.util.ArrayList;
import java.util.List;

import com.daikit.graphql.data.output.GQLOrderByEntry;
import com.daikit.graphql.data.output.GQLPaging;

/**
 * List load result for {@link Entity1}
 * 
 * @author Thibaut Caselli
 *
 */
public class Entity1ListLoadResult {

	private List<Entity1> data;
	private final List<GQLOrderByEntry> orderBy = new ArrayList<>();
	private GQLPaging paging;
	/**
	 * @return the data
	 */
	public List<Entity1> getData() {
		return data;
	}
	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<Entity1> data) {
		this.data = data;
	}
	/**
	 * @return the paging
	 */
	public GQLPaging getPaging() {
		return paging;
	}
	/**
	 * @param paging
	 *            the paging to set
	 */
	public void setPaging(GQLPaging paging) {
		this.paging = paging;
	}
	/**
	 * @return the orderBy
	 */
	public List<GQLOrderByEntry> getOrderBy() {
		return orderBy;
	}

}
