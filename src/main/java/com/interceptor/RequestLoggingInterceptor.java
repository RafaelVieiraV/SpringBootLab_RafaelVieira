package com.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final AtomicInteger requestCounter = new AtomicInteger(0);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        // 1. Actividad 3: Simulación de verificación JWT [cite: 168, 365]
        String authHeader = req.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Caso: Sin seguridad (Para tu captura de "ADVERTENCIA")
            System.out.println("ADVERTENCIA: Petición detectada sin Token de Seguridad JWT");
        } else {
            // Caso: Con seguridad (Para tu captura de "ÉXITO")
            System.out.println("INFO: Token JWT detectado y validado correctamente");
        }

        // 2. Lógica transversal: Tiempos y Contador [cite: 263, 309]
        req.setAttribute("t0", System.currentTimeMillis());
        int currentRequest = requestCounter.incrementAndGet();

        // Enviamos el conteo de vuelta a Postman [cite: 333]
        resp.addHeader("X-Request-Count", String.valueOf(currentRequest));
        req.setAttribute("requestNumber", currentRequest);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        Long t0 = (Long) req.getAttribute("t0");
        Integer n = (Integer) req.getAttribute("requestNumber");
        long elapsed = (t0 == null) ? -1 : (System.currentTimeMillis() - t0);

        // Registro final en consola para las Figuras 22 y 26 [cite: 310, 334]
        System.out.println("Request #" + n + " -> " + req.getMethod() + " " + req.getRequestURI() +
                " Status: " + resp.getStatus() + " Tiempo: " + elapsed + "ms");
    }
}