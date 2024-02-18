package com.suprun.seaBattleGame.entity;

/**
 * {@code Eser} class represents a user entity.
 *
 * @author Philip Suprun
 */
public class User {

    /**
     * Enum containing user roles assigned by administrator.
     */
    public enum UserRole {
        ADMIN,
        USER
    }

    private String name;
    private int age;
    private String password;
    private int victoriesCount;
    private int lesionsCount;
    private Long blockTime;
    private boolean isActive;
    private UserRole role;

    public User() {

    }

    public static UserBuilder builder() {
        return new User().new UserBuilder();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public int getVictoriesCount() {
        return victoriesCount;
    }

    public int getLesionsCount() {
        return lesionsCount;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Long blockTime) {
        this.blockTime = blockTime;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (victoriesCount != user.victoriesCount) return false;
        if (lesionsCount != user.lesionsCount) return false;
        if (isActive != user.isActive) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (blockTime != null ? !blockTime.equals(user.blockTime) : user.blockTime != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + victoriesCount;
        result = 31 * result + lesionsCount;
        result = 31 * result + (blockTime != null ? blockTime.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", password='").append(password).append('\'');
        sb.append(", victoriesCount=").append(victoriesCount);
        sb.append(", lesionsCount=").append(lesionsCount);
        sb.append(", blockTime=").append(blockTime);
        sb.append(", isActive=").append(isActive);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }

    public class UserBuilder {
        private UserBuilder() {

        }

        public UserBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public UserBuilder setAge(int age) {
            User.this.age = age;
            return this;
        }

        public UserBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public UserBuilder setVictoriesCount(int victoriesCount) {
            User.this.victoriesCount = victoriesCount;
            return this;
        }

        public UserBuilder setLesionsCount(int lesionsCount) {
            User.this.lesionsCount = lesionsCount;
            return this;
        }

        public UserBuilder setBlockTime(Long blockTime) {
            User.this.blockTime = blockTime;
            return this;
        }

        public UserBuilder setActive(boolean isActive) {
            User.this.isActive = isActive;
            return this;
        }

        public UserBuilder serRole(UserRole role) {
            User.this.role = role;
            return this;
        }

        public UserBuilder of(User user) {
            User.this.name = user.name;
            User.this.age = user.age;
            User.this.password = user.password;
            User.this.victoriesCount = user.victoriesCount;
            User.this.lesionsCount = user.lesionsCount;
            User.this.blockTime = user.blockTime;
            User.this.isActive = user.isActive;
            User.this.role = user.role;
            return this;
        }

        public User build() {
            return User.this;
        }

    }
}
