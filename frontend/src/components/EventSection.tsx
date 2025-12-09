import { Gift, Calendar, Star } from 'lucide-react';

const events = [
  {
    id: 1,
    title: '신규 가입 이벤트',
    description: '지금 가입하고 무료 코인 100개 받기!',
    icon: Gift,
    bgGradient: 'from-teal-primary/25 to-teal-primary/5',
    iconBg: 'bg-teal-tertiary',
    buttonColor: 'bg-teal-tertiary text-teal-dark',
  },
  {
    id: 2,
    title: '매일 출석 체크',
    description: '출석만 해도 무료 이용권 증정',
    icon: Calendar,
    bgGradient: 'from-teal-darker/25 to-teal-darker/5',
    iconBg: 'bg-teal-secondary',
    buttonColor: 'bg-teal-secondary text-teal-dark',
  },
  {
    id: 3,
    title: '작품 리뷰 이벤트',
    description: '리뷰 작성하고 추첨으로 선물 받기',
    icon: Star,
    bgGradient: 'from-teal-dark/25 to-teal-dark/5',
    iconBg: 'bg-teal-tertiary',
    buttonColor: 'bg-teal-tertiary text-teal-dark',
  },
];

export function EventSection() {
  return (
    <section className="px-4 py-6 pb-8">
      <h2 className="text-[#0D0D0D] text-lg font-bold mb-4">진행 중인 이벤트</h2>

      <div className="flex flex-col gap-3">
        {events.map((event) => {
          const Icon = event.icon;
          return (
            <div
              key={event.id}
              className={`p-4 rounded-xl border border-teal-secondary/30 cursor-pointer hover:border-teal-tertiary hover:scale-[1.02] transition-all bg-gradient-to-r ${event.bgGradient}`}
            >
              <div className="flex items-start gap-4">
                {/* Icon */}
                <div
                  className={`w-12 h-12 rounded-full ${event.iconBg} flex items-center justify-center flex-shrink-0`}
                >
                  <Icon className="w-6 h-6 text-teal-dark" />
                </div>

                {/* Content */}
                <div className="flex-1 min-w-0">
                  <h3 className="text-[#0D0D0D] text-base font-semibold mb-1">
                    {event.title}
                  </h3>
                  <p className="text-teal-secondary text-sm">
                    {event.description}
                  </p>
                </div>

                {/* Button */}
                <button
                  className={`px-4 py-2 rounded-full text-sm font-semibold flex-shrink-0 hover:opacity-90 transition-opacity ${event.buttonColor}`}
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
