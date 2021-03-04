package com.core.entity;

import java.io.Serializable;

public class Role implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.name
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.state
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.remark
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.name
     *
     * @return the value of role.name
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.name
     *
     * @param name the value for role.name
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.state
     *
     * @return the value of role.state
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.state
     *
     * @param state the value for role.state
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.remark
     *
     * @return the value of role.remark
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.remark
     *
     * @param remark the value for role.remark
     *
     * @mbg.generated Wed Dec 13 15:29:38 CST 2017
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}