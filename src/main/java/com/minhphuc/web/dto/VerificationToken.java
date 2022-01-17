package com.minhphuc.web.dto;

import java.util.Calendar;
import java.util.Date;

public class VerificationToken {

    public class VerificationTokenEntity {

        private static final int EXPIRATION = 60 * 24;

        private Long id;

        private String token;

        private UserDto user;

        private Date expiryDate;


        public VerificationTokenEntity() {
            super();
        }

        public VerificationTokenEntity(final String token) {
            super();

            this.token = token;
            this.expiryDate = CalculateExpiryDate(EXPIRATION);
        }

        public VerificationTokenEntity(final String token, final UserDto user) {
            super();

            this.token = token;
            this.user = user;
            this.expiryDate = CalculateExpiryDate(EXPIRATION);
        }

        private Date CalculateExpiryDate(final int expiryTimeInMinutes) {
            // this class is API best for getTime Calendar
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(new Date().getTime());
            cal.add(Calendar.MINUTE, expiryTimeInMinutes);
            return new Date(cal.getTime().getTime());

        }

        public Long getId() {
            return id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserDto getUser() {
            return user;
        }

        public void setUser(UserDto user) {
            this.user = user;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
        }

        public void updateToken(final String token) {
            this.token = token;
            this.expiryDate = CalculateExpiryDate(EXPIRATION);
        }

        //

        @Override
        public int hashCode() {
            // The 17 and 31 hash code idea is from the classic Java Book - effective Java: item 9
            final int prime = 31;
            int result = 17;// can choice a diff value - ex: 1
            result = prime * result + ((getExpiryDate() == null) ? 0 : getExpiryDate().hashCode());
            result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
            result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final VerificationTokenEntity other = (VerificationTokenEntity) obj;
            if (getExpiryDate() == null) {
                if (other.getExpiryDate() != null) {
                    return false;
                }
            } else if (!getExpiryDate().equals(other.getExpiryDate())) {
                return false;
            }
            if (getUser() == null) {
                if (other.getUser() != null) {
                    return false;
                }
            } else if (!getUser().equals(other.getUser())) {
                return false;
            }
            if (getToken() == null) {
                if (other.getToken() != null) {
                    return false;
                }
            } else if (!getToken().equals(other.getToken())) {
                return false;
            }
            return true;
        }

    }
}
