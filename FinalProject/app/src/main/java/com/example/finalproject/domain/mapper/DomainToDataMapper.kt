package com.example.finalproject.domain.mapper

import com.example.finalproject.data.models.authorization.AuthRequest
import com.example.finalproject.data.models.authorization.RegisterRequest
import com.example.finalproject.data.models.chat.AuthorDTO
import com.example.finalproject.data.models.chat.ChatRequest
import com.example.finalproject.data.models.courses.CourseRequest
import com.example.finalproject.data.models.courses.TextDTO
import com.example.finalproject.data.models.notes.CommentDTO
import com.example.finalproject.data.models.notes.CommentNoteRequest
import com.example.finalproject.data.models.notes.ContentDTO
import com.example.finalproject.data.models.notes.LocalNoteEntityDTO
import com.example.finalproject.data.models.notes.NoteDTO
import com.example.finalproject.data.models.notes.PostNoteRequest
import com.example.finalproject.data.models.profile.ChangeRoleRequest
import com.example.finalproject.data.models.profile.PhoneVisibilityRequest
import com.example.finalproject.domain.models.Author
import com.example.finalproject.domain.models.Comment
import com.example.finalproject.domain.models.Content
import com.example.finalproject.domain.models.Note
import com.example.finalproject.domain.models.Text
import com.example.finalproject.domain.security.PasswordHasher
import javax.inject.Inject

class DomainToDataMapper @Inject constructor(
    private val passwordHash: PasswordHasher
) {

    //Auth request
    fun authRequestToData(phone: String, password: String): AuthRequest {
        return AuthRequest(
            phone = phone,
            passwordHashed = passwordHash.hash(password = password)
        )
    }

    //Register request
    fun registerRequestToData(
        phone: String,
        password: String,
        name: String,
        surname: String,
        avatar: String
    ): RegisterRequest {
        return RegisterRequest(
            phone = phone,
            passwordHashed = passwordHash.hash(password = password),
            name = name,
            surname = surname,
            avatar = avatar
        )
    }

    // Post course request
    fun postCourseRequestToData(
        description: String,
        tags: List<String>,
        text: List<Text>,
        title: String
    ): CourseRequest {
        return CourseRequest(
            description = description,
            tags = tags,
            textDTO = text.map { it.toData() },
            title = title
        )
    }

    private fun Text.toData(): TextDTO {
        return TextDTO(
            text = text,
            image = image
        )
    }

    //Send message request
    fun textToChatRequest(text: String): ChatRequest {
        return ChatRequest(text)
    }

    //Post note request
    fun postNoteRequestToData(title: String, image: String, text: String): PostNoteRequest {
        val content = Content(image = image, text = text)
        return PostNoteRequest(
            title = title,
            contentDTO = content.toData()
        )
    }

    private fun Content.toData(): ContentDTO {
        return ContentDTO(
            text = text,
            image = image
        )
    }

    //Comment note request
    fun commentNoteRequest(text: String): CommentNoteRequest {
        return CommentNoteRequest(
            text = text
        )
    }

    //Change role request
    fun changeRoleToData(password: String): ChangeRoleRequest {
        return ChangeRoleRequest(
            passwordHashed = passwordHash.hash(password = password)
        )
    }

    //Changer phone visibility request
    fun changePhoneVisibilityToData(isVisible: Boolean): PhoneVisibilityRequest {
        return PhoneVisibilityRequest(isVisible)
    }

    //Local notes

    fun Note.toData(): NoteDTO {
        if (author != null) {
            return NoteDTO(
                id = id,
                title = title,
                content = content.map { it.toData() },
                author = author.toData(),
                date = date,
                comments = comments.map { it.toData() }
            )
        } else {
            throw RuntimeException("not community note")
        }
    }

    fun Note.toLocalData(): LocalNoteEntityDTO {
        return LocalNoteEntityDTO(
            id = id,
            title = title,
            content = content.map { it.toData() },
            isFavourite = isFavourite,
            date = date,
            comments = comments.map { it.toData() }
        )
    }

    private fun Author.toData(): AuthorDTO {
        return AuthorDTO(
            avatar = avatar,
            id = id,
            name = name,
            surname = surname,
            role = role
        )
    }

    private fun Comment.toData(): CommentDTO {
        return CommentDTO(
            id = id,
            author = author.toData(),
            text = text
        )
    }
}