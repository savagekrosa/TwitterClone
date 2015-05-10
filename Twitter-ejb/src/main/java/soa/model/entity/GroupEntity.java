package soa.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "groups")
public class GroupEntity {
    private String groupiId;
    private Collection<UserEntity> usersInGroup;

    @Id
    @Column(name = "groupid")
    public String getGroupiId() {
        return groupiId;
    }

    public void setGroupiId(String groupId) {
        this.groupiId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntity that = (GroupEntity) o;

        if (groupiId != null ? !groupiId.equals(that.groupiId) : that.groupiId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return groupiId != null ? groupiId.hashCode() : 0;
    }

    @OneToMany(mappedBy = "userGroup")
    public Collection<UserEntity> getUsersInGroup() {
        return usersInGroup;
    }

    public void setUsersInGroup(Collection<UserEntity> usersInGroup) {
        this.usersInGroup = usersInGroup;
    }
}
