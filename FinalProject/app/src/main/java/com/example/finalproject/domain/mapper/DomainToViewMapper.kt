package com.example.finalproject.domain.mapper

import androidx.compose.ui.res.stringResource
import com.example.finalproject.domain.models.Author
import com.example.finalproject.domain.models.Chat
import com.example.finalproject.domain.models.ChatItem
import com.example.finalproject.domain.models.Comment
import com.example.finalproject.domain.models.Content
import com.example.finalproject.domain.models.Note
import com.example.finalproject.domain.models.Profile
import com.example.finalproject.ui.models.AuthorVO
import com.example.finalproject.ui.models.ChatItemVO
import com.example.finalproject.ui.models.ChatVO
import com.example.finalproject.ui.models.CommentVO
import com.example.finalproject.ui.models.ContentVO
import com.example.finalproject.ui.models.NoteVO
import com.example.finalproject.ui.models.ProfileVO

class DomainToViewMapper {

    fun Profile.toView(): ProfileVO {
        return ProfileVO(
            id = id,
            name = name,
            surname = surname,
            courses = courses,
            notes = notes.map { it.toView() },
            role = mapRole(role = role),
            phone = mapPhone(phone = phone),
            registerDate = mapDateToDayMonthYear(registerDate),
            avatar = avatar
        )
    }

    fun Chat.toView(): ChatVO {
        return ChatVO(
            listChatItems = listChatItems.map { it.toView() }
        )
    }

    private fun ChatItem.toView(): ChatItemVO {
        return ChatItemVO(
            author = author.toView(),
            date = mapDateToDayMonthTime(date),
            id = id,
            message = message
        )
    }

    fun Note.toView(): NoteVO {
        return NoteVO(
            id = id,
            title = title,
            author = author?.toView(),
            date = mapAllNoteDate(date),
            content = content.map { it.toView() },
            comments = comments.map { it.toView() },
            isFavourite = isFavourite
        )
    }

    private fun Author.toView(): AuthorVO {
        return AuthorVO(
            avatar = avatar,
            id = id,
            name = name,
            surname = surname,
            role = mapRole(role)
        )
    }

    private fun Comment.toView(): CommentVO {
        return CommentVO(
            id = id,
            text = text,
            author = author.toView()
        )
    }

    private fun Content.toView(): ContentVO {
        return ContentVO(
            image = image,
            text = text
        )
    }

    private fun mapRole(role: Int): String {
        return if (role == 2) {
            "Админ"
        } else {
            "Студент"
        }
    }

    private fun mapRole(role: String): String {
        return if (role == "2") {
            "Админ"
        } else {
            "Студент"
        }
    }

    private fun mapDateToDayMonthTime(dateStr: String): String {
        // Проверяем, содержит ли строка дату и время в нужном формате
        val regex = """(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2})(?::(\d{2}))?""".toRegex()
        val matchResult = regex.find(dateStr)

        // Проверяем, что дата и время были успешно разобраны
        if (matchResult != null) {
            // Извлекаем группы из совпадений
            val (year, month, day, hours, minutes) = matchResult.destructured

            // Формируем строку в нужном формате "день.месяц часы:минуты"
            return String.format("%02d.%02d %02d:%02d", day.toInt(), month.toInt(), hours.toInt(), minutes.toInt())
        } else {
            // Если строка не соответствует ожидаемому формату, возвращаем сообщение об ошибке
            return "Invalid date format"
        }
    }



    private fun mapDateToDayMonthYear(dateStr: String): String {
        // Разбираем строку даты на компоненты
        val (year, month, day) = dateStr.split("T")[0].split("-").map { it.toInt() }

        // Формируем строку в нужном формате "день.месяц.год"
        return String.format("%02d.%02d.%d", day, month, year)
    }

    private fun mapPhone(phone: String): String {
        return when (phone.length) {
            11 -> {
                // Если номер состоит из 11 цифр
                val countryCode = phone.substring(0, 1)  // 7
                val areaCode = phone.substring(1, 4)     // 999
                val firstPart = phone.substring(4, 7)    // 999
                val secondPart = phone.substring(7, 9)   // 99
                val thirdPart = phone.substring(9, 11)   // 99

                // Форматируем номер телефона в нужный формат
                "+$countryCode ($areaCode) $firstPart-$secondPart-$thirdPart"
            }
            6 -> {
                // Если номер состоит из 6 цифр
                val firstPart = phone.substring(0, 3)    // 345
                val secondPart = phone.substring(3, 6)   // 546

                // Форматируем номер телефона в нужный формат
                "$firstPart-$secondPart"
            }
            else -> {
                // Если формат номера не поддерживается
                phone
            }
        }
    }


    private fun mapAllNoteDate(dateStr: String): String {
        // Разбираем строку даты вручную
        val (year, month, day) = dateStr.split("T")[0].split("-").map { it.toInt() }

        // Создаем карту для перевода месяцев на русский язык
        val monthMap = mapOf(
            1 to "января", 2 to "февраля", 3 to "марта", 4 to "апреля",
            5 to "мая", 6 to "июня", 7 to "июля", 8 to "августа",
            9 to "сентября", 10 to "октября", 11 to "ноября", 12 to "декабря"
        )

        // Получаем название месяца
        val monthName = monthMap[month] ?: throw IllegalArgumentException("Unknown month")

        // Формируем строку в нужном формате "день месяц год"
        return "$day $monthName $year"
    }

}