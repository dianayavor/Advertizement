package model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Advert implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private User author;

    @NotNull
    private LocalDate dateOfCreation;

    @NotNull
    private LocalDate dateOfEnd;

    @NotNull
    private Heading heading;

    @NotNull
    private Boolean isActive;

    public Advert() {
    }

    public Advert(@NotNull String title, @NotNull String description, @NotNull User author, @NotNull LocalDate dateOfCreation, @NotNull LocalDate dateOfEnd, @NotNull Heading heading, @NotNull Boolean isActive) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.dateOfCreation = dateOfCreation;
        this.dateOfEnd = dateOfEnd;
        this.heading = heading;
        this.isActive = isActive;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDate dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", dateOfCreation=" + dateOfCreation +
                ", dateOfEnd=" + dateOfEnd +
                ", heading=" + heading +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advert)) return false;
        Advert advert = (Advert) o;
        return id.equals(advert.id) && title.equals(advert.title) && description.equals(advert.description) && author.equals(advert.author) && dateOfCreation.equals(advert.dateOfCreation) && dateOfEnd.equals(advert.dateOfEnd) && heading == advert.heading && isActive.equals(advert.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, author, dateOfCreation, dateOfEnd, heading, isActive);
    }
}