package com.pd.pojo;

import java.io.Serializable;
import java.util.Date;

public class PdCartItem implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.user_id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.item_id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Long itemId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.num
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.status
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.created
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pd_cart_item.updated
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pd_cart_item
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.id
     *
     * @return the value of pd_cart_item.id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.id
     *
     * @param id the value for pd_cart_item.id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.user_id
     *
     * @return the value of pd_cart_item.user_id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.user_id
     *
     * @param userId the value for pd_cart_item.user_id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.item_id
     *
     * @return the value of pd_cart_item.item_id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.item_id
     *
     * @param itemId the value for pd_cart_item.item_id
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.num
     *
     * @return the value of pd_cart_item.num
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.num
     *
     * @param num the value for pd_cart_item.num
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.status
     *
     * @return the value of pd_cart_item.status
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.status
     *
     * @param status the value for pd_cart_item.status
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.created
     *
     * @return the value of pd_cart_item.created
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.created
     *
     * @param created the value for pd_cart_item.created
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pd_cart_item.updated
     *
     * @return the value of pd_cart_item.updated
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pd_cart_item.updated
     *
     * @param updated the value for pd_cart_item.updated
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_cart_item
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", itemId=").append(itemId);
        sb.append(", num=").append(num);
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}