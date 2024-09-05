package com.example.finalproject.data.mapper

import com.example.finalproject.data.models.chat.AuthorDTO
import com.example.finalproject.data.models.chat.ChatDTO
import com.example.finalproject.data.models.chat.ChatResponse
import com.example.finalproject.data.models.courses.AllCourseResponse
import com.example.finalproject.data.models.courses.CourseDTO
import com.example.finalproject.data.models.courses.CourseResponse
import com.example.finalproject.data.models.courses.TextDTO
import com.example.finalproject.data.models.notes.CommentDTO
import com.example.finalproject.data.models.notes.ContentDTO
import com.example.finalproject.data.models.notes.LocalNoteEntityDTO
import com.example.finalproject.data.models.notes.NoteDTO
import com.example.finalproject.data.models.notes.NotesResponse
import com.example.finalproject.data.models.notes.SingleNoteResponse
import com.example.finalproject.data.models.profile.ProfilesDTO
import com.example.finalproject.data.models.profile.ProfilesResponse
import com.example.finalproject.data.models.profile.SingleProfileDTO
import com.example.finalproject.data.models.profile.SingleProfileResponse
import com.example.finalproject.domain.models.Author
import com.example.finalproject.domain.models.Chat
import com.example.finalproject.domain.models.ChatItem
import com.example.finalproject.domain.models.Comment
import com.example.finalproject.domain.models.Content
import com.example.finalproject.domain.models.Course
import com.example.finalproject.domain.models.Courses
import com.example.finalproject.domain.models.Note
import com.example.finalproject.domain.models.Notes
import com.example.finalproject.domain.models.Profile
import com.example.finalproject.domain.models.ProfileItem
import com.example.finalproject.domain.models.Profiles
import com.example.finalproject.domain.models.Text

class DataToDomainMapper {

    //Courses

    fun CourseResponse.toDomain(): Course {
        return Course(
            description = data.description,
            id = data.id,
            tags = data.tags,
            text = data.text.map { it.toDomain() },
            title = data.title
        )
    }

    fun AllCourseResponse.toDomain(): Courses {
        return Courses(
            courses = data.map { it.toDomain() }
        )
    }

    private fun CourseDTO.toDomain(): Course {
        return Course(
            description = description,
            id = id,
            tags = tags,
            text = text.map { it.toDomain() },
            title = title
        )
    }

    private fun TextDTO.toDomain(): Text {
        return Text(
            image = image,
            text = text
        )
    }

    //Notes

    fun SingleNoteResponse.toDomain(): Note {
        return Note(
            id = data.id ?: "It's empty",
            title = data.title ?: "It's empty",
            content = data.content?.map { it.toDomain() } ?: listOf(),
            author = data.author?.toDomain(),
            date = data.date ?: "It's empty",
            comments = data.comments?.map { it.toDomain() } ?: listOf()
        )
    }

    fun NotesResponse.toDomain(): Notes {
        return Notes(
            notes = data.map { it.toDomain() }
        )
    }

    fun NoteDTO.toDomain(): Note {
        return Note(
            id = id ?: "It's empty",
            title = title ?: "It's empty",
            content = content?.map { it.toDomain() } ?: listOf(),
            author = author?.toDomain(),
            date = date ?: "It's empty",
            comments = comments?.map { it.toDomain() } ?: listOf()
        )
    }

    private fun AuthorDTO.toDomain(): Author {
        return Author(
            id = id ?: "It's empty",
            avatar = avatar ?: "It's empty",
            name = name ?: "It's empty",
            surname = surname ?: "It's empty",
            role = role ?: "It's empty"
        )
    }

    private fun ContentDTO.toDomain(): Content {
        return Content(
            image = image ?: "It's empty",
            text = text ?: "It's empty",
        )
    }

    private fun CommentDTO.toDomain(): Comment {
        return Comment(
            id = id,
            author = author.toDomain(),
            text = text
        )
    }

    //LocalNotes

    fun LocalNoteEntityDTO.toDomain(): Note {
        return Note(
            id = id,
            title = title,
            content = content.map { it.toDomain() },
            author = null,
            date = date,
            comments = comments.map { it.toDomain() }
        )
    }

    //Chat

    fun ChatResponse.toDomain(): Chat {
        return Chat(
            listChatItems = data.map { it.toDomain() }
        )
    }

    fun ChatDTO.toDomain(): ChatItem {
        return ChatItem(
            author = author.toDomain(),
            date = date,
            id = id,
            message = message
        )
    }

    //Profile

    fun ProfilesResponse.toDomain(): Profiles {
        return Profiles(
            profiles = data.map { it.toDomain() }
        )
    }

    fun SingleProfileDTO.toDomain(): Profile {
        return Profile(
            id = id,
            name = name,
            surname = surname,
            avatar = avatar,
            role = role,
            phone = phone?: "***********",
            registerDate = registerDate,
            courses = courses.map { it.toDomain() },
            notes = notes.map { it.toDomain() }
        )
    }

    fun SingleProfileResponse.toDomain(): Profile {
        return Profile(
            id = data.id,
            name = data.name,
            surname = data.surname,
            avatar = data.avatar,
            role = data.role,
            phone = data.phone?: "***********",
            registerDate = data.registerDate,
            courses = data.courses.map { it.toDomain() },
            notes = data.notes.map { it.toDomain() }
        )
    }

    private fun ProfilesDTO.toDomain(): ProfileItem {
        return ProfileItem(
            id = id ?: "It's empty",
            name = name ?: "It's empty",
            surname = surname ?: "It's empty",
            avatar = avatar ?: "It's empty"
        )
    }

}
