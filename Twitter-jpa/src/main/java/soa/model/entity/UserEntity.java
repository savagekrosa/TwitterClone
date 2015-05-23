package soa.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NamedQuery(name="UserEntity.findByUsername", query = "select user from UserEntity user where user.username = :name")
@Entity
@Table(name = "users")
public class UserEntity {
    private int userId;
    private String mail;
    private String username;
    private String password;
    private List<CommentEntity> commentsByUser;
    private Collection<PostEntity> postsByUser;
    private GroupEntity userGroup;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "author")
    public List<CommentEntity> getCommentsByUser() {
        return commentsByUser;
    }

    public void setCommentsByUser(List<CommentEntity> commentsByUser) {
        this.commentsByUser = commentsByUser;
    }

    @OneToMany(mappedBy = "author")
    public Collection<PostEntity> getPostsByUser() {
        return postsByUser;
    }

    public void setPostsByUser(Collection<PostEntity> postsByUser) {
        this.postsByUser = postsByUser;
    }

    @ManyToOne
    @JoinColumn(name = "usergroup", referencedColumnName = "groupid", nullable = false)
    public GroupEntity getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(GroupEntity userGroup) {
        this.userGroup = userGroup;
    }
}
