import { Gift, Calendar, Star } from 'lucide-react';

interface Event {
  eventId: number;
  title: string;
  imageUrl?: string;
  startAt?: string;
  endAt?: string;
}

interface EventSectionProps {
  events?: Event[];
}

// 아이콘 매핑 (순환)
const iconList = [Gift, Calendar, Star];
const bgGradients = [
  'from-teal-primary/25 to-teal-primary/5',
  'from-teal-darker/25 to-teal-darker/5',
  'from-teal-dark/25 to-teal-dark/5',
];
const iconBgs = ['bg-teal-tertiary', 'bg-teal-secondary', 'bg-teal-tertiary'];
const buttonColors = [
  'bg-teal-tertiary text-teal-dark',
  'bg-teal-secondary text-teal-dark',
  'bg-teal-tertiary text-teal-dark',
];

// 기본 이벤트 (API 로딩 중 또는 데이터 없을 때 표시)
const defaultEvents: Event[] = [
  { eventId: 1, title: '신규 가입 이벤트', startAt: '2025-12-01', endAt: '2026-01-01' },
  { eventId: 2, title: '매일 출석 체크', startAt: '2025-11-15', endAt: '2026-02-28' },
  { eventId: 3, title: '작품 리뷰 이벤트', startAt: '2025-12-05', endAt: '2026-01-10' },
];

export function EventSection({ events = defaultEvents }: EventSectionProps) {
  return (
    <section className="px-4 py-6 pb-8">
      <h2 className="text-[#0D0D0D] text-lg font-bold mb-4">진행 중인 이벤트</h2>

      <div className="flex flex-col gap-3">
        {events.map((event, index) => {
          const Icon = iconList[index % iconList.length];
          const bgGradient = bgGradients[index % bgGradients.length];
          const iconBg = iconBgs[index % iconBgs.length];
          const buttonColor = buttonColors[index % buttonColors.length];

          return (
            <div
              key={event.eventId}
              className={`p-4 rounded-xl border border-teal-secondary/30 cursor-pointer hover:border-teal-tertiary hover:scale-[1.02] transition-all bg-gradient-to-r ${bgGradient}`}
            >
              <div className="flex items-start gap-4">
                {/* Icon */}
                <div
                  className={`w-12 h-12 rounded-full ${iconBg} flex items-center justify-center flex-shrink-0`}
                >
                  <Icon className="w-6 h-6 text-teal-dark" />
                </div>

                {/* Content */}
                <div className="flex-1 min-w-0">
                  <h3 className="text-[#0D0D0D] text-base font-semibold mb-1">
                    {event.title}
                  </h3>
                  <p className="text-teal-secondary text-sm">
                    {event.startAt && event.endAt && `${event.startAt.split(' ')[0]} ~ ${event.endAt.split(' ')[0]}`}
                  </p>
                </div>

                {/* Button */}
                <button
                  className={`px-4 py-2 rounded-full text-sm font-semibold flex-shrink-0 hover:opacity-90 transition-opacity ${buttonColor}`}
                >
                  참여
                </button>
              </div>
            </div>
          );
        })}
      </div>
    </section>
  );
}
