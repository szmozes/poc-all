package hu.szmozes.filterspecification.service

import org.springframework.stereotype.Service

@Service
class HeavyCpuLoadService {
    fun getFibonacci(serial: Int): Int {
        if (serial == 0) {
            return 1;
        }
        if (serial == 1) {
            return 1
        }
        return getFibonacci(serial - 1) + getFibonacci(serial - 2)
    }
}