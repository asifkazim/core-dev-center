package az.core.security.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

    public class JwtToken {

        private String idToken;
        private String  expiryDate;

        public JwtToken(String idToken, String expiryDate) {
            this.idToken = idToken;
            this.expiryDate = expiryDate;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }
    }
