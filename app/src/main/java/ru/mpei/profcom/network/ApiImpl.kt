package ru.mpei.profcom.network

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.mpei.profcom.entry.model.UserData
import ru.mpei.profcom.main.model.entities.*

class ApiImpl: Api {

    private val users = listOf(
        UserData(1, "test@test.ru", "test", "A-01-18", "88005553535", "pb"),
        UserData(2, "test@test.ru", "test", "A-01-18", "88005553535", "president")
    )

    override fun register(
        email: String?,
        password: String?,
        group: String?,
        cardNumber: String?
    ): Completable = Completable.fromAction {
        Thread.sleep(1100L)
    }

    override fun entry(email: String?, password: String?): Single<UserData> = Single.fromCallable {
        if (email == "pb" && password == "test")
            users[0]
        else if (email == "president" && password == "test") {
            users[1]
        } else {
            throw IllegalArgumentException("Неверный логин или пароль!")
        }
    }

    override fun setUserType(
        email: String?,
        type: String?,
        pbId: Int
    ): Single<Boolean> = Single.just(true)

    override fun getNews(): Single<MutableList<NewsDto>> = Single.just(mutableListOf(
        NewsDto("TEST", "First test title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970"),
        NewsDto("TEST", "First test title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970"),
        NewsDto("TEST", "First test title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970"),
        NewsDto("TEST", "First test title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970"),
        NewsDto("TEST", "First test title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970"),
    ))

    override fun getInfo(): Single<MutableList<InfoDto>> = Single.just(mutableListOf(
        InfoDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://pro-dachnikov.com/uploads/posts/2021-11/1637969234_128-pro-dachnikov-com-p-mak-foto-132.jpg"),
        InfoDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://pro-dachnikov.com/uploads/posts/2021-11/1637969234_128-pro-dachnikov-com-p-mak-foto-132.jpg"),
        InfoDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://pro-dachnikov.com/uploads/posts/2021-11/1637969234_128-pro-dachnikov-com-p-mak-foto-132.jpg"),
        InfoDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://pro-dachnikov.com/uploads/posts/2021-11/1637969234_128-pro-dachnikov-com-p-mak-foto-132.jpg"),
        InfoDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://pro-dachnikov.com/uploads/posts/2021-11/1637969234_128-pro-dachnikov-com-p-mak-foto-132.jpg"),
        InfoDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://pro-dachnikov.com/uploads/posts/2021-11/1637969234_128-pro-dachnikov-com-p-mak-foto-132.jpg")
    ))

    override fun getOrgs(): Single<MutableList<OrgDto>> = Single.just(mutableListOf(
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album"),
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album"),
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album"),
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album"),
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album"),
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album"),
        OrgDto("TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://sun1-84.userapi.com/impg/ofSp23EbCiazvBO-P-hE7jfJ6KQcx84FI8Jb8A/KWq2Sg9wZnw.jpg?size=1667x1667&quality=95&sign=bb10b24968becf5f0c796c158d9c65d6&type=album")
    ))

    override fun getUserData(id: Int): Single<UserData> = Single.just(users[id-1])

    override fun addEvent(
        id: Int,
        name: String?,
        description: String?,
        link: String?
    ): Completable = Completable.fromAction {
        Thread.sleep(1000L)
    }

    override fun getEvents(id: Int): Single<MutableList<EventDto>> = Single.just(mutableListOf(
        EventDto(1, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://vk.com/profcom_mpei"),
        EventDto(2, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://vk.com/profcom_mpei"),
        EventDto(3, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://vk.com/profcom_mpei"),
        EventDto(4, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://vk.com/profcom_mpei"),
        EventDto(5, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://vk.com/profcom_mpei"),
        EventDto(6, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "https://vk.com/profcom_mpei")
    ))

    override fun addActiveRequest(id: Int, description: String?): Completable = Completable.fromAction {
        Thread.sleep(1000L)
    }

    override fun getLearnings(): Single<MutableList<LearnDto>> = Single.just(mutableListOf(
        LearnDto(1, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        LearnDto(2, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        LearnDto(3, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        LearnDto(4, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        LearnDto(5, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        LearnDto(6, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
        LearnDto(7, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
    ))

    override fun getTasks(id: Int): Single<MutableList<TaskDto>> = Single.just(mutableListOf(
        TaskDto(1, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970", "02.01.1970"),
        TaskDto(2, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970", "02.01.1970"),
        TaskDto(3, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970", "02.01.1970"),
        TaskDto(4, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970", "02.01.1970"),
        TaskDto(5, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970", "02.01.1970"),
        TaskDto(6, "TEST", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "01.01.1970", "02.01.1970"),
    ))

    override fun completeTask(id: Int): Completable = Completable.fromAction {
        Thread.sleep(1000L)
    }
}