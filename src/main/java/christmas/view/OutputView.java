package christmas.view;

import christmas.domain.Event;
import christmas.domain.Menu;
import christmas.domain.Reservation;

import java.util.Map;
import java.util.Optional;

public class OutputView {
    private static final String GREETING = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU = "<주문 메뉴>";
    private static final String TOTAL_ORDER_PRICE = "<할인 전 총주문 금액>";
    private static final String LINE_BREAK = "\n";
    private static final String ORDER_MENU_FORMAT = "%s %d개";
    private static final String PRICE_FORMAT = "%,d원";
    private static final String GIFT_MENU = "<증정 메뉴>";
    private static final String GIFT_MENU_COUNT = " 1개";
    private static final String NONE = "없음";
    private static final String BENEFITS = "<혜택 내역>";
    private static final String BENEFITS_FORMAT = "%s: -%,d원";

    public void showGreeting() {
        System.out.println(GREETING);
    }

    public static void showError(String message) {
        System.out.println(message);
    }

    public void showReservationInfo(Reservation reservation) {
        System.out.println(String.format(EVENT_PREVIEW, reservation.getVisitDate().getDay()));
        System.out.print(LINE_BREAK);

        System.out.println(ORDER_MENU);
        reservation.getOrders().stream()
                .map(order -> String.format(ORDER_MENU_FORMAT, order.getMenu().getName(), order.getCount()))
                .forEach(System.out::println);
        System.out.print(LINE_BREAK);

        System.out.println(TOTAL_ORDER_PRICE);
        System.out.println(String.format(PRICE_FORMAT, reservation.getTotalOrderPrice()));
        System.out.print(LINE_BREAK);
    }

    public void showGift(Optional<Menu> giftMenu) {
        System.out.println(GIFT_MENU);
        String output = NONE;
        if (giftMenu.isPresent()) {
            output = giftMenu.get().getName() + GIFT_MENU_COUNT;
        }
        System.out.println(output + LINE_BREAK);
    }

    public void showBenefits(Map<Event, Integer> benefitsByEvent) {
        System.out.println(BENEFITS);
        if (benefitsByEvent.isEmpty()) {
            System.out.println(NONE);
            return;
        }
        for (Map.Entry<Event, Integer> entry : benefitsByEvent.entrySet()) {
            String eventName = entry.getKey().getEventName();
            int benefits = entry.getValue();
            System.out.println(String.format(BENEFITS_FORMAT, eventName, benefits));
        }
    }
}
