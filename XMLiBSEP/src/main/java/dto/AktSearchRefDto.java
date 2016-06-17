package dto;

import java.io.Serializable;

public class AktSearchRefDto implements Serializable {
	
   private String aktId;
   private boolean approved;
   
   public AktSearchRefDto() {
	// TODO Auto-generated constructor stub
   }

   public String getAktId() {
	   return aktId;
   }

   public void setAktId(String aktId) {
	   this.aktId = aktId;
   }

   public boolean isApproved() {
	   return approved;
   }

   public void setApproved(boolean approved) {
	   this.approved = approved;
   }
   
   
   
   
}
