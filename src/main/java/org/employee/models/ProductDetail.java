package org.employee.models;

import org.springframework.stereotype.Component;



@Component
public class ProductDetail {
		private int productId;
		private String productName;
		private String description;
		private float sCost;
		private float Lprice;
		private String category;
		
		
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public float getsCost() {
			return sCost;
		}
		public void setsCost(float sCost) {
			this.sCost = sCost;
		}
		public float getLprice() {
			return Lprice;
		}
		public void setLprice(float lprice) {
			Lprice = lprice;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		
}
