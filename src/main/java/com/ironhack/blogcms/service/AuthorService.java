package com.ironhack.blogcms.service;

import com.ironhack.blogcms.dto.AuthorRequest;
import com.ironhack.blogcms.dto.AuthorResponse;
import com.ironhack.blogcms.exception.ConflictException;
import com.ironhack.blogcms.exception.ResourceNotFoundException;
import com.ironhack.blogcms.model.Author;
import com.ironhack.blogcms.repository.AuthorRepository;
import com.ironhack.blogcms.repository.BlogPostRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BlogPostRepository blogPostRepository;

    public AuthorService(AuthorRepository authorRepository, BlogPostRepository blogPostRepository) {
        this.authorRepository = authorRepository;
        this.blogPostRepository = blogPostRepository;
    }

    @Transactional(readOnly = true)
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream().map(AuthorResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public AuthorResponse findById(Long id) {
        return AuthorResponse.from(getAuthor(id));
    }

    @Transactional
    public AuthorResponse create(AuthorRequest request) {
        return AuthorResponse.from(authorRepository.save(new Author(request.name())));
    }

    @Transactional
    public AuthorResponse update(Long id, AuthorRequest request) {
        Author author = getAuthor(id);
        author.setName(request.name());
        return AuthorResponse.from(author);
    }

    /**
     * Deletes an author. Refuses with 409 while the author still has posts, so deleting
     * an author never silently deletes or orphans their posts.
     */
    @Transactional
    public void delete(Long id) {
        Author author = getAuthor(id);
        if (blogPostRepository.existsByAuthorId(id)) {
            throw new ConflictException("Author with id " + id + " still has posts; delete or reassign them first");
        }
        authorRepository.delete(author);
    }

    Author getAuthor(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", id));
    }
}
