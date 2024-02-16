package com.suprun.seaBattleGame.entity;


public class User {
    private String name;
    private int age;
    private String password;
    private int victoriesCount;
    private int lesionsCount;
    private Long blockTime;
    private boolean isActive;

    public User() {

    }

    public static PlayerBuilder builder() {
        return new User().new PlayerBuilder();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVictoriesCount() {
        return victoriesCount;
    }

    public void setVictoriesCount(int victoriesCount) {
        this.victoriesCount = victoriesCount;
    }

    public int getLesionsCount() {
        return lesionsCount;
    }

    public void setLesionsCount(int lesionsCount) {
        this.lesionsCount = lesionsCount;
    }

    public Long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(Long blockTime) {
        this.blockTime = blockTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User player = (User) o;

        if (age != player.age) return false;
        if (victoriesCount != player.victoriesCount) return false;
        if (lesionsCount != player.lesionsCount) return false;
        if (isActive != player.isActive) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (password != null ? !password.equals(player.password) : player.password != null) return false;
        return blockTime != null ? blockTime.equals(player.blockTime) : player.blockTime == null;
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
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", password='").append(password).append('\'');
        sb.append(", victoriesCount=").append(victoriesCount);
        sb.append(", lesionsCount=").append(lesionsCount);
        sb.append(", blockTime=").append(blockTime);
        sb.append(", isActive=").append(isActive);
        sb.append('}');
        return sb.toString();
    }

    public class PlayerBuilder {
        private PlayerBuilder() {

        }

        public PlayerBuilder setName(String name) {
            User.this.name = name;
            return this;
        }

        public PlayerBuilder setAge(int age) {
            User.this.age = age;
            return this;
        }

        public PlayerBuilder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public PlayerBuilder setVictoriesCount(int victoriesCount) {
            User.this.victoriesCount = victoriesCount;
            return this;
        }

        public PlayerBuilder setLesionsCount(int lesionsCount) {
            User.this.lesionsCount = lesionsCount;
            return this;
        }

        public PlayerBuilder setBlockTime(Long blockTime) {
            User.this.blockTime = blockTime;
            return this;
        }

        public PlayerBuilder setActive(boolean isActive) {
            User.this.isActive = isActive;
            return this;
        }
        public User build() {
            return User.this;
        }

    }
}
