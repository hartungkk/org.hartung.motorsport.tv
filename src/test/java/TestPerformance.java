import Controllers.RequestController;
import io.qameta.allure.Description;
import org.junit.Test;



public class TestPerformance {

    @Test
    @Description("Тест на проверку времени ответа на запрос получения всех каналов для неавторизованного пользователя в регионе usa_canada")
    public void testGetAllChannels() {
        RequestController requestController = new RequestController("/api/content/front/channel/list");

        requestController.getRequest();
    }
}

