package soa.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "HashtagEntity.findAll", query = "select hashtag from HashtagEntity hashtag"),
        @NamedQuery(name = "HashtagEntity.findTaggedPosts", query = "select hashtag.taggedPosts from HashtagEntity hashtag where hashtag.hashtag = :tag"),
        @NamedQuery(name = "HashtagEntity.findByTag", query = "select hashtag from HashtagEntity hashtag where hashtag.hashtag = :tag")
})
@Entity
@Table(name = "hashtags")
public class HashtagEntity {
    private String hashtag;
    private List<PostEntity> taggedPosts;

    public HashtagEntity() {
        taggedPosts = new ArrayList<>();
    }

    @Id
    @Column(name = "hashtag")
    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    @ManyToMany(mappedBy = "hashtags", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<PostEntity> getTaggedPosts() {
        return taggedPosts;
    }

    public void setTaggedPosts(List<PostEntity> taggedPosts) {
        this.taggedPosts = taggedPosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashtagEntity that = (HashtagEntity) o;

        if (hashtag != null ? !hashtag.equals(that.hashtag) : that.hashtag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hashtag != null ? hashtag.hashCode() : 0;
    }


}
