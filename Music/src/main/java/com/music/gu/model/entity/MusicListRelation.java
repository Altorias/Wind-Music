package com.music.gu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName music_list_relation
 */
@TableName(value ="music_list_relation")
@Data
public class MusicListRelation implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "music_list_id")
    private Integer musicListId;

    /**
     *
     */
    @TableField(value = "music_id")
    private Integer musicId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MusicListRelation other = (MusicListRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMusicListId() == null ? other.getMusicListId() == null : this.getMusicListId().equals(other.getMusicListId()))
            && (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMusicListId() == null) ? 0 : getMusicListId().hashCode());
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", play_list_id=").append(musicListId);
        sb.append(", music_id=").append(musicId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
